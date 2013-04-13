package org.abqjug;

import com.google.common.base.Predicate;
import com.google.common.collect.Collections2;
import com.google.common.collect.Lists;
import org.testng.annotations.Test;

import java.util.Collection;

import static org.fest.assertions.Assertions.assertThat;
import static org.testng.Assert.assertEquals;

public class PredicatesTest {


    @Test(groups = "unit")
    public void testFilter() {
        Collection<Integer> unfiltered = Lists.<Integer>newArrayList(1, 5, 6, 8, 9, 10, 44, 55, 19);
        assertEquals(Collections2.filter(unfiltered, new Predicate<Integer>() {
            public boolean apply(Integer input) {
                return input % 2 != 0;
            }
        }).
                toString(), "[1, 5, 9, 55, 19]");
    }

    @Test
    public void testPredicateBasic() {
        Predicate<Integer> isOdd = new Predicate<Integer>() {
            public boolean apply(Integer input) {
                return input % 2 != 0;
            }
        };

        Collection<Integer> unfiltered = Lists.<Integer>newArrayList
        (1, 5, 6, 8, 9, 10, 44, 55, 19);

        Collection<Integer> filteredList =
                Collections2.filter(unfiltered, isOdd);

        assertThat(filteredList.contains(21)).isFalse(); //Obvious
        unfiltered.add(21);
        assertThat(filteredList.contains(21)).isTrue();
        filteredList.add(23);
        assertThat(filteredList.contains(23)).isTrue();
        assertThat(unfiltered.contains(23)).isTrue();

        filteredList.add(40);   //Will fail

    }
}
