package com.gildedrose;

class GildedRose {
    Item[] items;

    private static String AGED_BRIE = "Aged Brie";
    private static String BACKSTAGE_PASSES = "Backstage passes to a TAFKAL80ETC concert";
    private static String SULFURAS = "Sulfuras, Hand of Ragnaros";

    public GildedRose(Item[] items) {
        this.items = items;
    }

    public void updateQuality() {
        for (int i = 0; i < items.length; i++) {
            Item item = items[i];
            handleQualityChange(item);
            decrementSellIn(item);
            handlePastSellInQuality(item);
        }
    }

    private void handlePastSellInQuality(Item item) {
        if (isSulfuras(item) || !itemPastSellIn(item)) return;

        if (isBackstagePasses(item)) zeroOutQuality(item);
        else if (isAgedBrie(item)) handleAgedBrie(item);
        else if (isDepreciatingQualityItem(item)) decrementQuality(item, 1);
    }

    private void zeroOutQuality(Item item) {
        decrementQuality(item, item.quality);
    }

    private boolean itemPastSellIn(Item item) {
        return item.sellIn < 0;
    }

    private void handleQualityChange(Item item) {
        if (isAppreciatingQualityItem(item)) {
            handleAppreciatingQualityItem(item);
        } else if (isDepreciatingQualityItem(item)){
            decrementQuality(item, 1);
        }
    }

    private void decrementQuality(Item item, int decrement) {
        if (itemHasQuality(item)) {
            item.quality = item.quality - decrement;
        }
    }

    private void decrementSellIn(Item item) {
        if (isSulfuras(item)) return;

        item.sellIn = item.sellIn - 1;
    }

    private void handleAgedBrie(Item item) {
        increaseQuality(item, 1);
    }

    private void handleAppreciatingQualityItem(Item item) {
        if (isAgedBrie(item)) handleAgedBrie(item);
        else if (isBackstagePasses(item)) handleBackstagePasses(item);
    }

    private void handleBackstagePasses(Item backstagePasses) {
        if (backstagePasses.sellIn < 6) increaseQuality(backstagePasses, 3);
        else if (backstagePasses.sellIn < 11) increaseQuality(backstagePasses, 2);
        else if (backstagePasses.sellIn > 0) increaseQuality(backstagePasses, 1);
    }

    private void increaseQuality(Item item, Integer increment) {
        if (qualityMaxedOut(item)) return;

        item.quality = item.quality + increment;
    }

    private boolean isAgedBrie(Item item) {
        return item.name.equals(AGED_BRIE);
    }

    private boolean isAppreciatingQualityItem(Item item) {
        return isAgedBrie(item) || isBackstagePasses(item);
    }

    private boolean isBackstagePasses(Item item) {
        return item.name.equals(BACKSTAGE_PASSES);
    }

    private boolean isDepreciatingQualityItem(Item item) {
        return !isSulfuras(item) && !isAppreciatingQualityItem(item);
    }

    private boolean isSulfuras(Item item) {
        return item.name.equals(SULFURAS);
    }

    private boolean itemHasQuality(Item item) {
        return item.quality > 0;
    }

    private boolean qualityMaxedOut(Item item) {
        return item.quality >= 50;
    }

}