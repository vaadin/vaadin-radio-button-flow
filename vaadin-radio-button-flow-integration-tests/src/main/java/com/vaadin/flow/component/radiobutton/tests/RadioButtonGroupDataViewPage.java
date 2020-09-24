package com.vaadin.flow.component.radiobutton.tests;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.NativeButton;
import com.vaadin.flow.component.radiobutton.RadioButtonGroup;
import com.vaadin.flow.component.radiobutton.dataview.RadioButtonGroupDataView;
import com.vaadin.flow.component.radiobutton.dataview.RadioButtonGroupListDataView;
import com.vaadin.flow.data.provider.AbstractDataProvider;
import com.vaadin.flow.data.provider.Query;
import com.vaadin.flow.router.Route;

@Route("radio-Button-group-data-view")
public class RadioButtonGroupDataViewPage extends Div {

    private static final String FIRST = "first";
    private static final String SECOND = "second";
    private static final String CHANGED_1 = "changed-1";
    private static final String CHANGED_2 = "changed-2";

    public RadioButtonGroupDataViewPage() {
        createGenericDataViewRadioButtonGroup();
        createListDataViewRadioButtonGroup();
        createAddItemByDataViewRadioButtonGroup();
        createFilterItemsByDataViewRadioButtonGroup();
    }

    private void createGenericDataViewRadioButtonGroup() {
        Item first = new Item(1L, FIRST);
        Item second = new Item(2L, SECOND);

        List<Item> items = new ArrayList<>(Arrays.asList(first, second));
        GenericDataProvider dataProvider = new GenericDataProvider(items);

        RadioButtonGroup<Item> rgbForDataView = new RadioButtonGroup<>();
        rgbForDataView.setId("rgbForDataView");

        RadioButtonGroupDataView<Item> dataView = rgbForDataView
                .setItems(dataProvider);
        dataView.setIdentifierProvider(Item::getId);

        NativeButton dataViewUpdateButton = new NativeButton("Update",
                click -> {
                    first.setValue(CHANGED_1);
                    second.setValue(CHANGED_2);

                    dataView.refreshItem(new Item(1L));
                });
        dataViewUpdateButton.setId("updBtnGdpDv");

        add(rgbForDataView, dataViewUpdateButton);
    }

    private void createListDataViewRadioButtonGroup() {
        Item first = new Item(1L, FIRST);
        Item second = new Item(2L, SECOND);

        RadioButtonGroup<Item> rgbForListDataView = new RadioButtonGroup<>();
        rgbForListDataView.setId("rgbForListDataView");

        RadioButtonGroupListDataView<Item> dataView = rgbForListDataView
                .setItems(first, second);
        dataView.setIdentifierProvider(Item::getId);

        NativeButton dataViewUpdateButton = new NativeButton("Update",
                click -> {
                    first.setValue(CHANGED_1);
                    second.setValue(CHANGED_2);

                    dataView.refreshItem(new Item(1L));
                });
        dataViewUpdateButton.setId("updBtnLstDv");

        add(rgbForListDataView, dataViewUpdateButton);
    }

    private void createAddItemByDataViewRadioButtonGroup() {
        Item first = new Item(1L, FIRST);
        List<Item> items = new ArrayList<>();
        items.add(first);

        RadioButtonGroup<Item> rgbForAddToDataView = new RadioButtonGroup<>();
        rgbForAddToDataView.setId("rgbForAddToDataView");

        RadioButtonGroupListDataView<Item> dataView = rgbForAddToDataView
                .setItems(items);

        NativeButton dataViewUpdateButton = new NativeButton("Add", click -> {
            Item second = new Item(1L, SECOND);
            dataView.addItem(second);
        });
        dataViewUpdateButton.setId("addBtnLstDv");

        add(rgbForAddToDataView, dataViewUpdateButton);
    }

    private void createFilterItemsByDataViewRadioButtonGroup() {
        RadioButtonGroup<Integer> numbers = new RadioButtonGroup<>();
        numbers.setId("rgbForFilterDataView");
        RadioButtonGroupListDataView<Integer> numbersDataView = numbers
                .setItems(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

        NativeButton filterOdds = new NativeButton("Filter Odds", click ->
            numbersDataView.setFilter(i -> i % 2 == 0)
        );
        filterOdds.setId("filterOdds");

        NativeButton noFilter = new NativeButton("No Filter", click ->
            numbersDataView.removeFilters()
        );
        noFilter.setId("noFilter");

        add(numbers, filterOdds, noFilter);
    }

    private static class GenericDataProvider
            extends AbstractDataProvider<Item, Void> {
        private final transient List<Item> items;

        public GenericDataProvider(List<Item> items) {
            this.items = items;
        }

        @Override
        public boolean isInMemory() {
            return true;
        }

        @Override
        public int size(Query<Item, Void> query) {
            return 2;
        }

        @Override
        public Stream<Item> fetch(Query<Item, Void> query) {
            return Stream.of(items.toArray(new Item[0]));
        }
    }

    private static class Item {
        private long id;
        private String value;

        public Item(long id) {
            this.id = id;
        }

        public Item(long id, String value) {
            this.id = id;
            this.value = value;
        }

        public long getId() {
            return this.id;
        }

        public String getValue() {
            return this.value;
        }

        public void setId(long id) {
            this.id = id;
        }

        public void setValue(String value) {
            this.value = value;
        }

        public boolean equals(Object o) {
            if (this == o) {
                return true;
            } else if (o != null && this.getClass() == o.getClass()) {
                Item item = (Item) o;
                return this.id == item.id
                        && Objects.equals(this.value, item.value);
            } else {
                return false;
            }
        }

        public int hashCode() {
            return Objects.hash(this.id, this.value);
        }

        public String toString() {
            return String.valueOf(this.value);
        }
    }
}
