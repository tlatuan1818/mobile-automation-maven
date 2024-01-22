package utils.helper;

import org.apache.commons.io.IOUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFClientAnchor;
import org.apache.poi.xssf.usermodel.XSSFDrawing;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class ExcelHelper {



    public static void updateCellWithImage(Workbook workbook, int rowNum, int colNum, XSSFDrawing drawing, XSSFClientAnchor inputImageAnchor, String inputImageName) throws IOException {
        InputStream inputImageStream = new FileInputStream(inputImageName);
        byte[] inputImageBytes = IOUtils.toByteArray(inputImageStream);
        int inputImagePictureID = workbook.addPicture(inputImageBytes, Workbook.PICTURE_TYPE_PNG);
        int colNum2 = colNum + 1;
        inputImageStream.close();
        inputImageAnchor.setCol1(colNum);
        inputImageAnchor.setRow1(rowNum - 1);
        inputImageAnchor.setCol2(colNum2);
        inputImageAnchor.setRow2(rowNum);
        drawing.createPicture(inputImageAnchor, inputImagePictureID);
    }
    public static String getCellData(Cell cell)  {
        try {
            String CellData = null;
            if (cell != null) {
                switch (cell.getCellType()) {
                    case STRING:
                        CellData = cell.getStringCellValue();

                        break;
                    case NUMERIC:
                        // Numeric value, handle accordingly (e.g., format as needed)
                        if (DateUtil.isCellDateFormatted(cell))
                        {
                            CellData = String.valueOf(cell.getDateCellValue());
                        }
                        else
                        {
                            CellData = String.valueOf((long)cell.getNumericCellValue());
                        }
                        break;
                    default:
                        // Handle other cell types if necessary
                        break;
                }
            }
            return CellData;
        } catch (Exception e) {
            return "";
        }
    }
}
