package com.mkwhitacre.spark.model;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class LegoSetTest {


    @Test
    public void parse(){
        //SetID,Number,Variant,Theme,Subtheme,Year,Name,Minifigs,Pieces,UKPrice,USPrice,CAPrice,EUPrice,ImageURL,Owned,Wanted,QtyOwned
        String knownValue = "22520,\"23\",\"2\",\"Town\",\"Product Collection\",\"1995\",\"Value Pack Italy\",,161,,,,,\"http://www.1000steine.com/brickset/images/23-2.jpg\",\"No\",\"No\",0";

        LegoSet set = LegoSet.parse(knownValue);

        assertThat(set.getSetId(), is(22520));
        assertThat(set.getNumber(), is("23"));
        assertThat(set.getVariant(), is("2"));
        assertThat(set.getTheme(), is("Town"));
        assertThat(set.getSubTheme(), is("Product Collection"));
        assertThat(set.getYear(), is("1995"));
        assertThat(set.getName(), is("Value Pack Italy"));
        assertThat(set.getMinifigs(), is(""));
        assertThat(set.getPieces(), is(161));
        assertThat(set.getUkPrice(), is(-1.0));
        assertThat(set.getUsPrice(), is(-1.0));
        assertThat(set.getCaPrice(), is(-1.0));
        assertThat(set.getEuPrice(), is(-1.0));
        assertThat(set.getImageUrl(), is("http://www.1000steine.com/brickset/images/23-2.jpg"));
        assertThat(set.isOwned(), is(false));
        assertThat(set.isWanted(), is(false));
        assertThat(set.getQtyOwned(), is(0));
    }
}
