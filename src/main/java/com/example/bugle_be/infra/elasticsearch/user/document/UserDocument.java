package com.example.bugle_be.infra.elasticsearch.user.document;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.*;

@Getter
@Builder
@Document(indexName = "users")
@Setting(settingPath = "elasticsearch/es-settings.json")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access =  AccessLevel.PRIVATE)
public class UserDocument {

    @Id
    private Long id;

    @MultiField(
        mainField = @Field(
            type = FieldType.Text,
            analyzer = "autocomplete_analyzer",
            searchAnalyzer = "autocomplete_search_analyzer"
        ),
        otherFields = {
            @InnerField(suffix = "keyword", type = FieldType.Keyword)
        }
    )
    private String accountId;

    @Field(
        type = FieldType.Text,
        analyzer = "autocomplete_analyzer",
        searchAnalyzer = "autocomplete_search_analyzer"
    )
    private String userName;

    @Field(type = FieldType.Keyword, index = false)
    private String profileImageObjectKey;
}
