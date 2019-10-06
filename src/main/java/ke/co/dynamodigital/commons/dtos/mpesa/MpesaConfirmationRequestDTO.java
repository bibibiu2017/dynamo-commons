package ke.co.dynamodigital.commons.dtos.mpesa;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Pattern;

/**
 * @author Bibibiu
 * created 2019-07-09 at 20:32
 **/
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MpesaConfirmationRequestDTO {

    @JsonProperty("TransactionType")
    private String transactionType;

    @JsonProperty("TransID")
    private String transId;

    @JsonProperty("TransTime")
    private String transTime;

    @JsonProperty("TransAmount")
    private String transAmount;

    @JsonProperty("BusinessShortCode")
    private String businessShortCode;

    @JsonProperty("BillRefNumber")
    @Pattern(regexp = "^\\d{11}$", message = "Must be 11 digits only")
    private String billRefNumber;

    @JsonProperty("InvoiceNumber")
    private String invoiceNumber;

    @JsonProperty("OrgAccountBalance")
    private String orgAccountBalance;

    @JsonProperty("ThirdPartyTransID")
    private String thirdPartyTransId;

    @JsonProperty("MSISDN")
    private String msisdn;

    @JsonProperty("FirstName")
    private String firstName;

    @JsonProperty("MiddleName")
    private String middleName;

    @JsonProperty("LastName")
    private String lastName;

}
