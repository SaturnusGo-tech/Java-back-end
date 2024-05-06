package com.virtoworks.omnia.test.checkPages.account.manageSupplier;

import com.virtoworks.omnia.utils.actions.account.ActionAccountManageSuppliers;
import com.virtoworks.omnia.utils.global.Config;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Fail.fail;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class TestManageSupplier {

    // Configuration instance
    private static final Config config = new Config();

    // ActionsCatalog instance
    private final ActionAccountManageSuppliers actionsCatalog = new ActionAccountManageSuppliers();

    /**
     * Set up method executed before all tests.
     * It sets up the test environment.
     */
    @BeforeAll
    public static void setUpAll() {
        config.setUpAll();
    }

    /**
     * Set up method executed before each test.
     * It sets up the test environment and loads color data.
     */
    @BeforeEach
    public void setUp() throws IOException {
        config.setUp("company/supplier-management");
        actionsCatalog.loadColorData();
    }
    /**
     * Test for clicking the "Buy now" button and retrieving the conversion page URL.
     * This test verifies that clicking the "Buy now" button leads to a valid conversion page URL.
     */
    @Test
    @Tag("BuyNow")
    public void BuyNowConversion__Url() {
        System.out.println("Starting 'Buy now' button test...");
        try {
            String conversionPageUrl = actionsCatalog.clickBuyNowAndGetConversionPageUrl();
            assertNotNull(conversionPageUrl, "Conversion page URL should not be null");
            System.out.println("Conversion page URL: " + conversionPageUrl);
        } catch (AssertionError error) {
            System.err.println("Test failed: " + error.getMessage());
            throw error;
        }
        System.out.println("'Buy now' button test passed successfully.");
    }

    /**
     * Test for clicking the "Upload certificate" button and uploading a file.
     * This test verifies that the file can be successfully uploaded after clicking the button.
     */
    @Test
    @Tag("UploadTaxCert")
    public void UploadCertificate__File() {
        System.out.println("Starting 'Upload certificate' and file upload test...");
        try {
            actionsCatalog.clickUploadCertificateButton();
            URL resourceUrl = getClass().getClassLoader().getResource("testData/taxCert.xlsx");
            assertNotNull(resourceUrl, "Resource 'taxCert.xlsx' should not be null");
            File fileToUpload = new File(resourceUrl.getFile());
            actionsCatalog.uploadCertificateFile(fileToUpload);
        } catch (Exception e) {
            System.err.println("Test failed: " + e.getMessage());
            fail("Test failed: " + e.getMessage());
        }
        System.out.println("'Upload certificate' and file upload test passed successfully.");
    }

    /**
     * Test for uploading multiple certificates.
     * This test verifies that multiple certificates can be uploaded successfully.
     */
    @Test
    @Tag("UploadMultipleTaxCerts")
    public void UploadMultiple__Certs() {
        System.out.println("Starting multiple certificates upload test...");
        try {
            actionsCatalog.clickUploadCertificateButton();
            String[] fileNames = {"taxCert.xlsx", "taxCert1.xlsx", "taxCert2.xlsx"};
            File[] filesToUpload = new File[fileNames.length];
            for (int i = 0; i < fileNames.length; i++) {
                URL resourceUrl = getClass().getClassLoader().getResource("testData/" + fileNames[i]);
                assertNotNull(resourceUrl, "Resource '" + fileNames[i] + "' should not be null");
                filesToUpload[i] = new File(resourceUrl.getFile());
            }
            actionsCatalog.uploadMultipleCertificates(filesToUpload);
        } catch (Exception e) {
            System.err.println("Test failed: " + e.getMessage());
            fail("Test failed: " + e.getMessage());
        }
        System.out.println("Multiple certificates upload test passed successfully.");
    }

    /**
     * Test for clicking the "Tax documents" button and asserting visibility of "Tax certification.docx" button.
     * This test verifies that clicking the "Tax documents" button leads to the visibility of "Tax certification.docx" button.
     */
    @Test
    @Tag("BrowseFilesUploadedData")
    public void BrowseFilesAssertTax__Docs() {
        System.out.println("Starting 'Tax documents' button and 'Tax certification.docx' visibility test...");
        try {
            actionsCatalog.clickBrowseFilesUploadedData();
            actionsCatalog.assertBrowseFilesUploadedDataTaxDocsVisible();
        } catch (Exception e) {
            System.err.println("Test failed: " + e.getMessage());
            fail("Test failed: " + e.getMessage());
        }
        System.out.println("'Tax documents' button and 'Tax certification.docx' visibility test passed successfully.");
    }

    /**
     * Test for verifying colors.
     * This test verifies that the colors on the page match the expected colors.
     */
    @Test
    @Tag("ColorVerification")
    public void VerifyColor() {
        System.out.println("Starting color verification test...");
        try {
            Thread.sleep(25000);
            for (Map.Entry<String, Map<String, String>> categoryEntry : actionsCatalog.getColorScheme().entrySet()) {
                String category = categoryEntry.getKey();
                Map<String, String> shades = categoryEntry.getValue();
                for (Map.Entry<String, String> shadeEntry : shades.entrySet()) {
                    String shade = shadeEntry.getKey();
                    actionsCatalog.verifyColor(category, shade);
                }
            }
            System.out.println("Color verification test passed successfully.");
        } catch (Exception e) {
            System.err.println("Error during color verification: " + e.getMessage());
            throw new RuntimeException(e);
        }
    }

    /**
     * Test for completing the upload process.
     * This test verifies that the entire upload process, including entering the name, selecting suppliers, and clicking the "Upload" button, works as expected.
     */
    @Test
    @Tag("CompleteUploadProcess")
    public void CompleteUploadProcess_4__several() {
        System.out.println("Starting 'Upload tax certs', enter name, select suppliers, and click 'Upload' button test...");
        try {
            String certificateName = "Sample Certificate Name";
            List<Integer> checkboxIndices = List.of(2, 3, 5, 7);
            actionsCatalog.clickUploadCertsEnterNameSelectSuppliersAndClickUpdateData(certificateName, checkboxIndices);
            URL resourceUrl = getClass().getClassLoader().getResource("testData/taxCert1.xlsx");
            assertNotNull(resourceUrl, "Resource 'taxCert1.xlsx' should not be null");
            File fileToUpload = new File(resourceUrl.getFile());
            actionsCatalog.uploadCertificateFile(fileToUpload);
            actionsCatalog.clickUpdateDataButton();
            Thread.sleep(3000);
            System.out.println("'Upload tax certs', enter name, select suppliers, and click 'Upload' button test passed successfully.");
        } catch (AssertionError error) {
            System.err.println("Test failed: " + error.getMessage());
            throw error;
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.err.println("Sleep interrupted: " + e.getMessage());
        }
    }

    /**
     * Test for completing the upload process.
     * This test verifies that the entire upload process, including entering the name, selecting suppliers, and clicking the "Upload" button, works as expected.
     */
    @Test
    @Tag("CompleteUploadProcess")
    public void CompleteUploadProcess_4__all() {
        System.out.println("Starting 'Upload tax certs', enter name, select suppliers, and click 'Upload' button test...");
        try {
            String certificateName = "Sample Certificate Name";
            List<Integer> checkboxIndices = List.of(1);
            actionsCatalog.clickUploadCertsEnterNameSelectSuppliersAndClickUpdateData(certificateName, checkboxIndices);
            URL resourceUrl = getClass().getClassLoader().getResource("testData/taxCert1.xlsx");
            assertNotNull(resourceUrl, "Resource 'taxCert1.xlsx' should not be null");
            File fileToUpload = new File(resourceUrl.getFile());
            actionsCatalog.uploadCertificateFile(fileToUpload);
            actionsCatalog.clickUpdateDataButton();
            Thread.sleep(3000);
            System.out.println("'Upload tax certs', enter name, select suppliers, and click 'Upload' button test passed successfully.");
        } catch (AssertionError error) {
            System.err.println("Test failed: " + error.getMessage());
            throw error;
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.err.println("Sleep interrupted: " + e.getMessage());
        }
    }

    /**
     * Test for clicking the "Browse Files" button and downloading a certificate.
     * This test verifies that clicking the "Browse Files" button allows downloading a certificate.
     */
    @Test
    @Tag("BrowseFilesAndDownloadAnyCert")
    public void BrowseDownloadCert__single() {
        System.out.println("Starting 'Browse Files and Download Any Cert' test...");
        try {
            Thread.sleep(3000);
            actionsCatalog.clickBrowseFilesAndDownloadAnyCert();
            Thread.sleep(10000);
            System.out.println("'Browse Files and Download Any Cert' test passed successfully.");
        } catch (AssertionError error) {
            System.err.println("Test failed: " + error.getMessage());
            throw error;
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.err.println("Sleep interrupted: " + e.getMessage());
        }
    }
}
