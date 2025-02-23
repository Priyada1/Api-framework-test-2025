package models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import lombok.extern.slf4j.Slf4j;

@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode (callSuper = false)
@Slf4j
public class CovidDataModel {

    @JsonProperty
    private String province;

    @JsonProperty
    private Integer total_tests;

    @JsonProperty
    private Integer total_recoveries;

    @JsonProperty
    private Integer total_fatalities;


    @JsonProperty
    private Integer total_boosters_1;

    @JsonProperty
    private Integer total_boosters_2;

}
