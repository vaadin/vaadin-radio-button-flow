/*
 * Copyright 2000-2020 Vaadin Ltd.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package com.vaadin.flow.component.radiobutton.dataview;

import com.vaadin.flow.component.radiobutton.RadioButtonGroup;
import com.vaadin.flow.data.provider.AbstractDataView;
import com.vaadin.flow.data.provider.DataProvider;
import com.vaadin.flow.data.provider.Query;
import com.vaadin.flow.function.SerializableSupplier;

/**
 * Implementation of generic data view for radioButton group.
 *
 * @param <T>
 *            the item type
 * @since
 */
public class RadioButtonGroupDataView<T> extends AbstractDataView<T> {

    /**
     * Constructs a new DataView.
     *
     * @param dataProviderSupplier
     *            data provider supplier
     * @param radioButtonGroup
     *            radioButton group instance for this DataView
     */
    public RadioButtonGroupDataView(
            SerializableSupplier<? extends DataProvider<T, ?>> dataProviderSupplier,
            RadioButtonGroup radioButtonGroup) {
        super(dataProviderSupplier, radioButtonGroup);
    }

    @Override
    protected Class<?> getSupportedDataProviderType() {
        return DataProvider.class;
    }

    @Override
    public T getItem(int index) {
        final int dataSize = dataProviderSupplier.get().size(new Query<>());
        if (dataSize == 0) {
            throw new IndexOutOfBoundsException(
                    String.format("Requested index %d on empty data.", index));
        }
        if (index < 0 || index >= dataSize) {
            throw new IndexOutOfBoundsException(String.format(
                    "Given index %d is outside of the accepted range '0 - %d'",
                    index, dataSize - 1));
        }
        return getItems().skip(index).findFirst().orElse(null);
    }
}