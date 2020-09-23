package com.vaadin.flow.component.radiobutton.tests;

import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.vaadin.flow.testutil.AbstractComponentIT;
import com.vaadin.flow.testutil.TestPath;

@TestPath("radio-Button-group-data-view")
public class RadioButtonGroupDataViewPageIT extends AbstractComponentIT {

    private static final String CHANGED_1 = "changed-1";
    private static final String VAADIN_RADIO_BUTTON = "vaadin-radio-button";
    private static final String FIRST = "first";
    private static final String SECOND = "second";

    private static boolean isOpened = false;

    @Before
    public void openPage() {

        if (isOpened)
            return;

        open();

        isOpened = true;
    }

    @Test
    public void testGenericDataView_refreshSingleItem_onlyReflectChangesOfThatItem() {

        findElement(By.id("updBtnGdpDv")).click();

        WebElement group = findElement(By.id("rgbForDataView"));
        List<WebElement> buttons = group
                .findElements(By.tagName(VAADIN_RADIO_BUTTON));

        Assert.assertEquals("Group should have buttons", 2, buttons.size());

        Assert.assertEquals("First RadioButton should be updated to",
                CHANGED_1, buttons.get(0).getText());

        Assert.assertEquals(
                "Second RadioButton should still holds the old value", SECOND,
                buttons.get(1).getText());
    }

    @Test
    public void testListDataView_refreshSingleItem_onlyReflectChangesOfThatItem() {

        findElement(By.id("updBtnLstDv")).click();

        WebElement group = findElement(By.id("rgbForListDataView"));
        List<WebElement> buttons = group
                .findElements(By.tagName(VAADIN_RADIO_BUTTON));

        Assert.assertEquals("Group should have buttons", 2, buttons.size());

        Assert.assertEquals("First RadioButton should be updated to",
                CHANGED_1, buttons.get(0).getText());

        Assert.assertEquals(
                "Second RadioButton should still holds the old value", SECOND,
                buttons.get(1).getText());
    }

    @Test
    public void testListDataView_addItem_shouldAddOneAndOnlyOneItem() {

        WebElement group = findElement(By.id("rgbForAddToDataView"));
        List<WebElement> buttons = group
                .findElements(By.tagName(VAADIN_RADIO_BUTTON));

        Assert.assertEquals("Group should have buttons", 1, buttons.size());

        findElement(By.id("addBtnLstDv")).click();

        waitForElementPresent(By.tagName(VAADIN_RADIO_BUTTON));

        group = findElement(By.id("rgbForAddToDataView"));
        buttons = group.findElements(By.tagName(VAADIN_RADIO_BUTTON));

        Assert.assertEquals("Group should have buttons", 2, buttons.size());

        Assert.assertEquals("First RadioButton should be updated to", FIRST,
                buttons.get(0).getText());

        Assert.assertEquals(
                "Second RadioButton should still holds the old value", SECOND,
                buttons.get(1).getText());
    }

    @Test
    public void testListDataView_addAndRemoveFilters_shouldProduceCorrectNumberOfRadioButtons() {

        WebElement group = findElement(By.id("rgbForFilterDataView"));
        List<WebElement> buttons = group
                .findElements(By.tagName(VAADIN_RADIO_BUTTON));

        Assert.assertEquals("Group should have buttons", 10, buttons.size());
        //
        findElement(By.id("filterOdds")).click();
        waitForElementPresent(By.tagName(VAADIN_RADIO_BUTTON));

        group = findElement(By.id("rgbForFilterDataView"));
        buttons = group.findElements(By.tagName(VAADIN_RADIO_BUTTON));

        Assert.assertEquals("Group should have buttons", 5, buttons.size());
        //
        findElement(By.id("noFilter")).click();
        waitForElementPresent(By.tagName(VAADIN_RADIO_BUTTON));

        group = findElement(By.id("rgbForFilterDataView"));
        buttons = group.findElements(By.tagName(VAADIN_RADIO_BUTTON));

        Assert.assertEquals("Group should have buttons", 10, buttons.size());
    }
}
