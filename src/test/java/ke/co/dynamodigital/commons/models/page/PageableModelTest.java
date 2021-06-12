package ke.co.dynamodigital.commons.models.page;

import ke.co.dynamodigital.commons.config.extension.WithSoftAssertions;
import lombok.*;
import org.junit.jupiter.api.*;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageImpl;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

@SpringBootTest
@ActiveProfiles("test")
class PageableModelTest extends WithSoftAssertions {

    @Autowired
    private ModelMapper modelMapper;

    @Test
    void map() {
        //GIVEN
        ModelA modelA = ModelA.builder().x(1).y(2).z(3).build();
        PageableModel<ModelA> pageableModel = PageableModel.<ModelA>builder()
                .page(0)
                .totalPages(1)
                .rows(1)
                .totalRows(1L)
                .items(List.of(modelA))
                .build();

        //WHEN
        PageableModel<ModelB> bPageableModel = pageableModel.map(modelA1 -> modelMapper.map(modelA1, ModelB.class));

        //THEN
        softly.assertThat(bPageableModel)
                .isNotNull()
                .isEqualToIgnoringGivenFields(pageableModel, "items")
                .extracting(PageableModel::getItems)
                .asList()
                .hasSize(1)
                .containsExactly(ModelB.builder().x(1).y(2).build());
    }

    @Test
    @DisplayName("should create pageable model from page")
    void shouldCreatePageableModelFromPage() {
        //given:
        ModelA modelA = ModelA.builder().x(1).y(2).z(3).build();
        var page = new PageImpl<>(List.of(modelA));

        //when:
        var pageableModel = PageableModel.from(page);

        //then:
        softly.assertThat(pageableModel).isNotNull()
                .satisfies(model -> {
                    softly.assertThat(model.getPage()).isEqualTo(page.getNumber());
                    softly.assertThat(model.getItems()).isEqualTo(page.getContent());
                    softly.assertThat(model.getRows()).isEqualTo(page.getNumberOfElements());
                    softly.assertThat(model.getTotalPages()).isEqualTo(page.getTotalPages());
                    softly.assertThat(model.getTotalRows()).isEqualTo(page.getTotalElements());
                });
    }

}

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
class ModelA {
    int x;
    int y;
    int z;
}

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
class ModelB {
    int x;
    int y;
}
