package com.virtoworks.omnia.utils.actions.account;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.virtoworks.omnia.utils.locators.accounts.manageSup.ManageSup;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static com.codeborne.selenide.Selenide.*;

/**
 *Documentation for the ActionAccountManageSuppliers class:
 <p>
 * This class provides methods for interacting with the tax certificate upload functionality.
 <p>
 * Tax Certificates Upload:
 * - File Limits:
 *   - Maximum file size: 10MB
 * - File Available Formats: Supports the following formats: docx, jpg, jpeg, pdf, png, txt, xlsx
 * - Upload Files:
 *   - For one supplier
 *   - For several suppliers
 *   - For all suppliers
 * - Download File:
 *   - The code contains a method to click the "Tax documents" button and then click cert button to download the certificate.
 */

public class ActionAccountManageSuppliers {

    private final ManageSup manageSup = new ManageSup();
    private final ManageSup.Status statusLocators = new ManageSup.Status();
    private final ManageSup.TaxCertification taxCertificationLocators = new ManageSup.TaxCertification();
    private Map<String, Map<String, String>> colorScheme;

    public Map<String, Map<String, String>> getColorScheme() {
        if (colorScheme == null) {
            throw new IllegalStateException("Color data has not been loaded.");
        }
        return colorScheme;
    }

    /**
     * Actions for getting query data using suppliers keywords
     */
    public void enterDataInSupplierInput(Integer supId, String data) {
        String supName = manageSup.SupData.get(supId);
        if (supName == null) {
            throw new IllegalArgumentException("Supplier ID not found: " + supId);
        }

        SelenideElement supplierInput = $$("input[placeholder='Search']")
                .filter(Condition.visible)
                .findBy(Condition.exist);

        if (supplierInput == null) {
            throw new IllegalStateException("Input field not found for supplier: " + supName);
        }

        supplierInput.setValue(data);
    }


    /**
     * Clicks the "Buy Now" button and returns the URL of the conversion page.
     *
     * @return The URL of the conversion page.
     */
    public String clickBuyNowAndGetConversionPageUrl() {
        SelenideElement buyNowButton = ManageSup.Status.getBuyNowButton();
        buyNowButton.shouldBe(Condition.visible).click();

        String conversionPageUrl = com.codeborne.selenide.WebDriverRunner.getWebDriver().getCurrentUrl();

        screenshot("conversion_page");
        System.out.println("Successfully navigated to the conversion page. URL: " + conversionPageUrl);

        return conversionPageUrl;
    }

    /**
     * Clicks the "Upload certificate" button.
     *
     * @throws Exception If unable to click the button.
     */
    public void clickUploadCertificateButton() throws Exception {
        SelenideElement uploadCertificateButton = ManageSup.TaxCertification.getUploadCertificateButton();
        try {
            uploadCertificateButton.shouldBe(Condition.visible).click();
            screenshot("upload_certificate_success");
            System.out.println("Clicked on 'Upload certificate' button.");
        } catch (Exception e) {
            screenshot("upload_certificate_failed");
            System.err.println("Failed to click on 'Upload certificate' button. Error: " + e.getMessage());
            throw e;
        }
    }

    /**
     * Makes the file input field visible using JavaScript and uploads a file.
     *
     * @param fileToUpload The file to upload.
     * @throws IllegalArgumentException If the file size exceeds 10MB or if the file format is not supported.
     */
    public void uploadCertificateFile(File fileToUpload) throws IllegalArgumentException {
        long fileSizeInBytes = fileToUpload.length();
        long fileSizeInMB = fileSizeInBytes / (1024 * 1024);
        if (fileSizeInMB > 10) {
            throw new IllegalArgumentException("File size exceeds the limit of 10MB.");
        }

        String fileName = fileToUpload.getName();
        String fileExtension = fileName.substring(fileName.lastIndexOf(".") + 1);
        List<String> allowedFormats = Arrays.asList("docx", "jpg", "jpeg", "pdf", "png", "txt", "xlsx");
        if (!allowedFormats.contains(fileExtension.toLowerCase())) {
            throw new IllegalArgumentException("File format '" + fileExtension + "' is not supported.");
        }

        SelenideElement fileInput = ManageSup.TaxCertification.getFileInput();

        try {
            executeJavaScript("arguments[0].style.display = 'block';", fileInput);

            fileInput.shouldBe(Condition.visible, Condition.enabled).uploadFile(fileToUpload);

            screenshot("file_uploaded_success");
            System.out.println("Uploaded file: " + fileName);
            System.out.println("File size: " + fileSizeInMB + "MB");
            System.out.println("File format: " + fileExtension);
        } catch (Exception e) {
            screenshot("file_uploaded_failed");
            System.err.println("Failed to upload file: " + fileName + ". Error: " + e.getMessage());
            throw e;
        }
    }

    /**
     * Uploads multiple certificates using the file input field.
     *
     * @param filesToUpload An array of files to upload.
     * @throws IllegalArgumentException If any file size exceeds 10MB or if any file format is not supported.
     */
    public void uploadMultipleCertificates(File[] filesToUpload) throws IllegalArgumentException {
        for (File file : filesToUpload) {
            long fileSizeInBytes = file.length();
            long fileSizeInMB = fileSizeInBytes / (1024 * 1024);
            if (fileSizeInMB > 10) {
                throw new IllegalArgumentException("File size exceeds the limit of 10MB: " + file.getName());
            }

            String fileName = file.getName();
            String fileExtension = fileName.substring(fileName.lastIndexOf(".") + 1);
            List<String> allowedFormats = Arrays.asList("docx", "jpg", "jpeg", "pdf", "png", "txt", "xlsx");
            if (!allowedFormats.contains(fileExtension.toLowerCase())) {
                throw new IllegalArgumentException("File format '" + fileExtension + "' is not supported: " + file.getName());
            }

            System.out.println("Uploaded file: " + fileName);
            System.out.println("File size: " + fileSizeInMB + "MB");
            System.out.println("File format: " + fileExtension);
        }

        SelenideElement fileInput = taxCertificationLocators.getFileInput();

        try {
            executeJavaScript("arguments[0].style.display = 'block';", fileInput);

            fileInput.shouldBe(Condition.visible, Condition.enabled).uploadFile(filesToUpload);

            screenshot("multiple_files_uploaded_success");
            System.out.println("Uploaded multiple files: ");
            for (File file : filesToUpload) {
                System.out.println("- " + file.getName());
            }
        } catch (Exception e) {
            screenshot("multiple_files_uploaded_failed");
            System.err.println("Failed to upload multiple files. Error: " + e.getMessage());
            throw e;
        }
    }

    /**
     * Clicks the "Tax documents" button.
     *
     * @throws Exception If unable to click the button.
     */
    public void clickBrowseFilesUploadedData() throws Exception {
        SelenideElement browseFilesUploadedData = taxCertificationLocators.getBrowseFilesUploadedData();
        try {
            browseFilesUploadedData.shouldBe(Condition.visible).click();
            screenshot("browse_files_uploaded_data_success");
            System.out.println("Clicked on 'Tax documents' button.");
        } catch (Exception e) {
            screenshot("browse_files_uploaded_data_failed");
            System.err.println("Failed to click on 'Tax documents' button. Error: " + e.getMessage());
            throw e;
        }
    }

    /**
     * Asserts that the "Tax certification.docx" button is visible.
     *
     * @throws Exception If unable to find the button.
     */
    public void assertBrowseFilesUploadedDataTaxDocsVisible() throws Exception {
        SelenideElement browseFilesUploadedDataTaxDocs = taxCertificationLocators.getBrowseFilesUploadedDataTaxDocs();
        try {
            browseFilesUploadedDataTaxDocs.shouldBe(Condition.visible);
            screenshot("browse_files_uploaded_data_tax_docs_success");
            System.out.println("'Tax certification.docx' button is visible.");
        } catch (Exception e) {
            screenshot("browse_files_uploaded_data_tax_docs_failed");
            System.err.println("Failed to find 'Tax certification.docx' button. Error: " + e.getMessage());
            throw e;
        }
    }

    /**
     * Load color data from a JSON resource file.
     *
     * @throws IOException If an I/O error occurs when reading from the input stream.
     */
    public void loadColorData() throws IOException {
        String resourcePath = "jsonData/manageSuppliers.colors/Colors.json";
        InputStream resourceStream = getClass().getClassLoader().getResourceAsStream(resourcePath);
        if (resourceStream == null) {
            System.err.println("Resource file not found: " + resourcePath);
            throw new IllegalArgumentException("Resource file not found: " + resourcePath);
        }
        try {
            ObjectMapper mapper = new ObjectMapper();
            colorScheme = mapper.readValue(resourceStream, new TypeReference<Map<String, Map<String, String>>>() {});
        } finally {
            resourceStream.close();
        }
        System.out.println("Colors loaded successfully: " + colorScheme);
    }
    /**
     * Verify CSS color value against expected value from JSON.
     *
     * @param colorCategory The category of the color.
     * @param colorShade    The shade of the color.
     */
    public void verifyColor(String colorCategory, String colorShade) {
        String cssVariableName = "--" + colorCategory + "-" + colorShade;
        String expectedColor = getColorScheme().get(colorCategory).get(colorShade);
        String actualColor = executeJavaScript("return getComputedStyle(document.documentElement).getPropertyValue('" + cssVariableName + "').trim();");

        if (!expectedColor.equalsIgnoreCase(actualColor)) {
            screenshot("color_mismatch_" + colorCategory + "_" + colorShade);
            throw new AssertionError("Color mismatch for " + cssVariableName + ": expected " + expectedColor + ", but found " + actualColor);
        }

        screenshot("color_verification_" + colorCategory + "_" + colorShade);
        System.out.println("Verified color " + cssVariableName + ": " + actualColor);
    }
    /**
     * Clicks the "Upload tax certs" button.
     *
     * @param uploadCertsButton The SelenideElement representing the "Upload tax certs" button.
     */
    public void clickUploadTaxCertsButton(SelenideElement uploadCertsButton) {
        try {
            uploadCertsButton.shouldBe(Condition.visible).click();
            screenshot("upload_tax_certs_button_clicked");
            System.out.println("Clicked on 'Upload tax certs' button.");
        } catch (Exception e) {
            screenshot("upload_tax_certs_button_failed");
            System.err.println("Failed to click 'Upload tax certs' button. Error: " + e.getMessage());
            throw e;
        }
    }

    /**
     * Enters the certificate name in the input field.
     *
     * @param certificateName The name of the certificate to enter.
     */
    public void enterCertificateName(String certificateName) {
        SelenideElement displayInputName = ManageSup.TaxCertification.getDisplayInputName();
        try {
            displayInputName.shouldBe(Condition.visible).click();
            executeJavaScript("arguments[0].value='" + certificateName + "';", displayInputName);
            screenshot("certificate_name_entered");
            System.out.println("Entered certificate name: " + certificateName);
        } catch (Exception e) {
            screenshot("enter_certificate_name_failed");
            System.err.println("Failed to enter certificate name. Error: " + e.getMessage());
            throw e;
        }
    }
    /**
     * Opens the suppliers dropdown and selects checkboxes.
     */
    public void openSuppliersDropdownAndSelectCheckboxes(List<Integer> checkboxIndices) {
        SelenideElement suppliersDropDown = ManageSup.TaxCertification.getSuppliersDropDown();
        SelenideElement arrowDownButton = ManageSup.TaxCertification.getArrowDown();

        try {
            suppliersDropDown.shouldBe(Condition.visible).click();
            screenshot("suppliers_dropdown_opened");
            System.out.println("Opened suppliers dropdown.");

            for (int index : checkboxIndices) {
                String checkboxXpath = manageSup.Supplier.get("Supplier " + index);
                if (checkboxXpath != null) {
                    $x(checkboxXpath).shouldBe(Condition.visible).click();
                    screenshot("checkbox_" + index + "_selected");
                    System.out.println("Checkbox " + index + " selected.");
                }
            }

            arrowDownButton.shouldBe(Condition.visible).click();
            screenshot("arrow_down_button_clicked");
            System.out.println("Clicked on the arrow down button to close the dropdown.");

        } catch (Exception e) {
            screenshot("open_suppliers_dropdown_select_checkboxes_failed");
            System.err.println("Failed to open suppliers dropdown, select checkboxes, or click arrow down button. Error: " + e.getMessage());
            throw e;
        }
    }
    /**
     * Clicks the "Upload" button to update data.
     */
    public void clickUpdateDataButton() {
        SelenideElement updateDataButton = ManageSup.TaxCertification.getUpdateDataButton();
        try {
            updateDataButton.shouldBe(Condition.visible).click();
            screenshot("update_data_button_clicked");
            System.out.println("Clicked on 'Upload' button to update data.");
        } catch (Exception e) {
            screenshot("update_data_button_failed");
            System.err.println("Failed to click 'Upload' button. Error: " + e.getMessage());
            throw e;
        }
    }

    /**
     * Clicks the "Upload tax certs" button, enters certificate name, selects suppliers, and clicks "Update Data".
     *
     * @param certificateName  The name of the certificate.
     * @param checkboxIndices The indices of the checkboxes to select.
     */
    public void clickUploadCertsEnterNameSelectSuppliersAndClickUpdateData(String certificateName, List<Integer> checkboxIndices) {
        try {
            SelenideElement uploadCertsButton = ManageSup.TaxCertification.getUploadCertsButton();
            clickUploadTaxCertsButton(uploadCertsButton);
            enterCertificateName(certificateName);
            openSuppliersDropdownAndSelectCheckboxes(checkboxIndices);
        } catch (Exception e) {
            screenshot("upload_certs_enter_name_select_suppliers_click_update_data_failed");
            System.err.println("Failed to upload certs, enter name, select suppliers, or click 'Upload'. Error: " + e.getMessage());
            throw e;
        }
    }
    /**
     * Clicks the "Tax documents" button and then clicks any button to download the certificate.
     */
    public void clickBrowseFilesAndDownloadAnyCert() {
        SelenideElement browseFilesUploadedData = ManageSup.TaxCertification.getBrowseFilesUploadedData();
        SelenideElement downloadButton = ManageSup.TaxCertification.DownloadTaxCertData();

        try {
            browseFilesUploadedData.shouldBe(Condition.visible).click();
            screenshot("browse_files_uploaded_data_clicked");
            System.out.println("Clicked on 'Tax documents' button.");

            downloadButton.shouldBe(Condition.visible).click();
            screenshot("download_cert_clicked");
            System.out.println("Clicked on the button to download the certificate.");

        } catch (Exception e) {
            screenshot("browse_files_and_download_cert_failed");
            System.err.println("Failed to click 'Tax documents' or download the certificate. Error: " + e.getMessage());
            throw e;
        }
    }
    public void enterSuppliersDataInput(Integer supID){

        String data = manageSup.SupData.get(supID);
        if (data == null){
            throw new IllegalArgumentException("Suppliers data is not found for ID" + supID);
        }
        ManageSup.getSearchInput()
                .shouldBe(Condition.visible, Condition.enabled)
                .setValue(data);
    }
}
