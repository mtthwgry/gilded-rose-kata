package com.gildedrose;

import static org.junit.Assert.*;

import org.junit.Test;

public class GildedRoseTest {

    @Test
    public void whenNormalItems_thenQualityDegradesTwiceAsFastAfterSellBy() {
        Item avocadoToast = new Item("Avocado Toast", 1, 16);
        Item[] items = new Item[] { avocadoToast };

        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(0, avocadoToast.sellIn);
        assertEquals(15, avocadoToast.quality);

        app.updateQuality();
        assertEquals(-1, avocadoToast.sellIn);
        assertEquals(13, avocadoToast.quality);
    }

    @Test
    public void whenNormalItems_thenQualityIsNeverLessThanZero() {
        Item sushi = new Item("Sushi", 0, 0);
        Item[] items = new Item[] { sushi };

        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertTrue(sushi.quality >= 0);
    }

    @Test
    public void whenAgedBrie_thenQualityIncreasesAfterSellDate() {
        Item agedBrie = new Item("Aged Brie", 1, 10);
        Item[] items = new Item[] { agedBrie };

        GildedRose app = new GildedRose(items);

        app.updateQuality();
        assertEquals(0, agedBrie.sellIn);
        assertTrue(agedBrie.quality > 10);

        int previousQuality = agedBrie.quality;
        app.updateQuality();
        assertEquals(-1, agedBrie.sellIn);
        assertTrue(agedBrie.quality > previousQuality);
    }

    @Test
    public void whenAgedBrie_thenQualityIsNeverMoreThanFifty() {
        Item agedBrieMaxQuality = new Item( "Aged Brie", 0, 50);
        Item[] items = new Item[] { agedBrieMaxQuality };

        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(50, agedBrieMaxQuality.quality);

        app.updateQuality();
        assertEquals(50, agedBrieMaxQuality.quality);
    }

    @Test
    public void whenSulfuras_thenQualityIsNeverDecreases() {
        Item sulfuras = new Item( "Sulfuras, Hand of Ragnaros", 5, 10);
        Item[] items = new Item[] { sulfuras };

        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(5, sulfuras.sellIn);
        assertEquals(10, sulfuras.quality);
    }

    @Test
    public void whenBackstagePass_thenQualityIncreasesByTwoWhenThereAreLessThanTenDaysLeft() {
        Item backstagePass = new Item( "Backstage passes to a TAFKAL80ETC concert", 11, 10);
        Item[] items = new Item[] { backstagePass };

        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(10, backstagePass.sellIn);
        assertEquals(11, backstagePass.quality);

        app.updateQuality();
        assertEquals(9, backstagePass.sellIn);
        assertEquals(13, backstagePass.quality);

        app.updateQuality();
        assertEquals(8, backstagePass.sellIn);
        assertEquals(15, backstagePass.quality);
    }

    @Test
    public void whenBackstagePass_thenQualityIncreasesByThreeWhenThereAreLessThanFiveDaysLeft() {
        Item backstagePass = new Item( "Backstage passes to a TAFKAL80ETC concert", 6, 10);
        Item[] items = new Item[] { backstagePass };

        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(5, backstagePass.sellIn);
        assertEquals(12, backstagePass.quality);

        app.updateQuality();
        assertEquals(4, backstagePass.sellIn);
        assertEquals(15, backstagePass.quality);

        app.updateQuality();
        assertEquals(3, backstagePass.sellIn);
        assertEquals(18, backstagePass.quality);
    }

    @Test
    public void whenBackstagePass_thenQualityIsZeroAfterSellBy() {
        Item backstagePass = new Item( "Backstage passes to a TAFKAL80ETC concert", 1, 10);
        Item[] items = new Item[] { backstagePass };

        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(0, backstagePass.sellIn);
        assertEquals(13, backstagePass.quality);

        app.updateQuality();
        assertEquals(-1, backstagePass.sellIn);
        assertEquals(0, backstagePass.quality);
    }

}
