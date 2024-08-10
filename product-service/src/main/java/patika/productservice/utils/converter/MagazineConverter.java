package patika.productservice.utils.converter;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import patika.productservice.entity.Magazine;
import patika.productservice.entity.dto.request.MagazineSaveRequest;
import patika.productservice.entity.dto.response.*;

import java.util.List;

/**
 * Copyright (c) 2024
 * All rights reserved.
 *
 * @author Emre Ünaldı
 * @since 20.06.2024
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class MagazineConverter {
    public static Magazine toMagazine(MagazineSaveRequest request) {
        return Magazine.builder()
                .name(request.getName())
                .amount(request.getAmount())
                .description(request.getDescription())
                .publishDate(request.getPublishDate())
                .publisherId(request.getPublisherId())
                .authorIds(request.getAuthorIds())
                .build();
    }

    public static MagazineResponse toMagazineResponse(Magazine magazine) {
        return MagazineResponse.builder()
                .id(magazine.getId())
                .name(magazine.getName())
                .amount(magazine.getAmount())
                .description(magazine.getDescription())
                .publishDate(magazine.getPublishDate())
                .publisherId(magazine.getPublisherId())
                .authorIds(magazine.getAuthorIds())
                .build();
    }

    public static List<MagazineResponse> toListMagazineResponse(List<Magazine> magazines) {
        return magazines.stream()
                .map(MagazineConverter::toMagazineResponse)
                .toList();
    }
}
