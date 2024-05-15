package com.virtoworks.omnia.utils.locators.accounts.manageSup;

import com.codeborne.selenide.SelenideElement;

import java.util.LinkedHashMap;
import java.util.Map;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

/**
 * Locator class for managing supplier-related elements.
 */
public class ManageSup {


    /**
     * Subclass for locating status-related elements.
     */
    public static class Status {
        /**
         * Locates the "Buy now" button.
         *
         * @return The "Buy now" button element.
         */
        public static SelenideElement getBuyNowButton() {
            return $(byText("Buy now"));
        }
    }
    /**
     * Locates the "Search" input.
     *
     * @return The "Search" input element.
     */
    public static SelenideElement getSearchInput() {
        return $("input[placeholder='Search']");
    }

    /**
     * Locates the second "decorator" button element.
     *
     * @return The second "decorator" button element.
     */
    public static SelenideElement getDecorator__btn() {
        return $x("//*[@id=\"app\"]/div/div[4]/div/div/div/div[2]/div[3]/div/div/div[1]/div[1]/button[2]");
    }

    public static SelenideElement getNext__btn() {
        return $(byText("Next"));
    }

    /**
     * Subclass for locating tax certification-related elements.
     */
    public static class TaxCertification {
        /**
         * Locates the "Upload certificate" button.
         *
         * @return The "Upload certificate" button element.
         */
        public static SelenideElement getUploadCertificateButton() {
            return $(byText("Upload certificate"));
        }

        /**
         * Locates the "Browse your files" button.
         *
         * @return The "Browse your files" button element.
         */
        public static SelenideElement getBrowseFilesButton() {
            return $(byText("Browse your files"));
        }

        /**
         * Locates the "Tax documents" button.
         *
         * @return The "Tax documents" button element.
         */
        public static SelenideElement getBrowseFilesUploadedData() {
            return $(byText("Tax documents"));
        }

        /**
         * Locates the "Tax certification.docx" button.
         *
         * @return The "Tax certification.docx" button element.
         */
        public static SelenideElement getBrowseFilesUploadedDataTaxDocs() {
            return $(byText("Tax certification.docx"));
        }

        /**
         * Locates the file input element.
         *
         * @return The file input element.
         */
        public static SelenideElement getFileInput() {
            return $(".vc-file-uploader.vc-file-uploader--view--vertical input[type='file']");
        }

        /**
         * Locates the "Upload tax certs" button.
         *
         * @return The "Upload tax certs" button element.
         */
        public static SelenideElement getUploadCertsButton() {
            return $(byText("Upload tax certs"));
        }

        /**
         * Locates the "Enter certificate name" input field.
         *
         * @return The "Enter certificate name" input field element.
         */
        public static SelenideElement getDisplayInputName() {
            return $("input[placeholder='Enter certificate name']");
        }


        /**
         * Locates the "Please select value(s)" input field for suppliers.
         *
         * @return The "Please select value(s)" input field element for suppliers.
         */
        public static SelenideElement getSuppliersDropDown() {
            return $("input[placeholder='Please select value(s)']");
        }


        /**
         * Locates the "Upload" button for updating data.
         *
         * @return The "Upload" button element for updating data.
         */
        public static SelenideElement getUpdateDataButton() {
            return $(byText("Upload"));
        }

        /**
         * Locates the element for downloading tax certification data.
         *
         * @return The element for downloading tax certification data.
         */
        public static SelenideElement DownloadTaxCertData() {

            return $(".divide-y");
        }
        /**
         * Locates the element for close up tax dropdown data.
         *
         * @return The element for deactivating dropdown data.
         */
        public static SelenideElement getArrowDown() {
            return $(".vc-select__arrow");
        }
    }

    // Map to store Supplier and States data
    public Map<String, String> Supplier;
    public Map<String, String> States;

    public Map<Integer, String> SupData;


    /**
     * Constructor to initialize Supplier and States data.
     */


    public ManageSup() {
        Supplier = new LinkedHashMap<>();
        for (int i = 1; i <= 18; i++) {
            Supplier.put("Supplier " + i, String.format("//*/ul/li[%d]/button/span/label/input", i));
        }

        States = new LinkedHashMap<>();
        for (int i = 1; i <= 51; i++) {
            States.put("Catalog " + i, String.format("//*/ul/li[%d]/button/span/label/input", i));
        }
        /**
         * Constructor for initialize sup name ID's
         */
        SupData = new LinkedHashMap<>();
        SupData.put(1, "ODP");
        SupData.put(2, "Global");
        SupData.put(3, "Quill");
        SupData.put(4, "Pocket");
        SupData.put(5, "Grainger");
        SupData.put(6, "MSC");
        SupData.put(7, "Lawson");

    }

}

