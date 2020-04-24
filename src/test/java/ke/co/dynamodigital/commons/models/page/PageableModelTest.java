package ke.co.dynamodigital.commons.models.page;

import com.github.rozidan.springboot.modelmapper.WithModelMapper;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ActiveProfiles("test")
class PageableModelTest {

    private SoftAssertions softly;

    @BeforeEach
    void setUp() {
        softly = new SoftAssertions();
    }

    @AfterEach
    void tearDown() {
        softly.assertAll();;
    }

    @Autowired
    private ModelMapper modelMapper;

    @Test
    void map() {
        //GIVEN
        ModelA modelA = ModelA.builder()
                .x(1)
                .y(2)
                .z(3)
                .build();
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

    @Configuration
    @WithModelMapper(basePackages = "ke.co.dynamodigital.commons")
    static class ModelMapperConfig {

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
