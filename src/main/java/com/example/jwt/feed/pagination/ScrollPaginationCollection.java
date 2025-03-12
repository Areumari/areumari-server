package com.example.jwt.feed.pagination;

import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class ScrollPaginationCollection<T> {

    private final List<T> itemsWithNextCursor;
    private final int countPerScroll;

    public static <T> ScrollPaginationCollection<T> of(List<T> itemsWithNextCursor, int size) {
        return new ScrollPaginationCollection<>(itemsWithNextCursor, size);
    }

    public boolean isLastScroll() {
        return this.itemsWithNextCursor.size() <= countPerScroll;
    }

    public List<T> getCurrentScrollItems() {
        return isLastScroll() ? this.itemsWithNextCursor : this.itemsWithNextCursor.subList(0, countPerScroll);
    }

    public T getNextCursor() {
        return isLastScroll() ? null : itemsWithNextCursor.get(countPerScroll);
    }
}