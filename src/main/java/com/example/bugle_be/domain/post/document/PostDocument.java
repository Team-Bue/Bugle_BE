package com.example.bugle_be.domain.post.document;

import lombok.Getter;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.AccessLevel;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.*;

@Getter
@Builder
@Document(indexName = "posts")
@Setting(settingPath = "elasticsearch/es-settings.json")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class PostDocument {

    @Id
    private Long id;

    @MultiField(
        mainField = @Field(
            type = FieldType.Text,
            analyzer = "nori_analyzer",
            searchAnalyzer = "nori_analyzer"
        ),
        otherFields = {
            @InnerField(
                suffix = "english",
                type = FieldType.Text,
                analyzer = "english"
            )
        }
    )
    private String content;

    @MultiField(
        mainField = @Field(
            type = FieldType.Text,
            analyzer = "nori_analyzer",
            searchAnalyzer = "nori_analyzer"
        ),
        otherFields = {
            @InnerField(
                suffix = "english",
                type = FieldType.Text,
                analyzer = "location_analyzer",
                searchAnalyzer = "location_analyzer"
            )
        }
    )
    private String location;

    @Field(type = FieldType.Keyword, index = false)
    private String objectKey;
}
