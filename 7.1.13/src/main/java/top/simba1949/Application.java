package top.simba1949;

import com.itextpdf.kernel.colors.ColorConstants;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.element.Text;
import com.itextpdf.layout.property.TextAlignment;

import java.io.File;
import java.io.IOException;

/**
 * @author anthony
 * @date 2021/1/26 23:12
 */
public class Application {
    public static void main(String[] args) throws IOException {
        // 文件名
        String fileName = getPdfFileName();
        // 文件对象
        File file = new File(fileName);

        // pdf 输出流
        PdfWriter pdfWriter = new PdfWriter(file);
        // 处理 pdf 的主入口点
        PdfDocument pdfDoc = new PdfDocument(pdfWriter);
        // 设置pdf的页面大小
        PageSize pageSize = new PageSize(PageSize.A4);
        // 文档对象，用于添加文档中的各种元素
        Document document = new Document(pdfDoc, pageSize);

        // document 元素只能添加 AreaBreak、Image对象和IBlockElement接口的实现类对象
        // document.add(createParagraph());
        // 对比是否存在首行缩进
        // document.add(new Paragraph("君不见黄河之水天上来，奔流到海不复回").setFont(createPdfFont()));
        document.add(createTable());

        // 文档的最后处理
        document.close();
    }

    /**
     * 创建字体对象
     * @return
     * @throws IOException
     */
    public static PdfFont createPdfFont() throws IOException {
        // 使用 PdfFontFactory 创建字体
        // 使用下面字体可以处理中文不显示的问题
        return PdfFontFactory.createFont("STSongStd-Light", "UniGB-UCS2-H", false);
    }

    /**
     * 创建 Table 对象
     * @return
     */
    public static Table createTable() throws IOException {
        // 创建几列的表格对象
        Table table = new Table(4);
        for (int i = 0; i < 2; i++) {
            if (i == 0){
                // 第一行数据，创建 Cell 对象，默认一行一列
                Cell cell00 = new Cell();
                cell00.add(new Paragraph("姓名").setFont(createPdfFont()));
                table.addCell(cell00);

                table.addCell(new Cell().add(new Paragraph("李白").setFont(createPdfFont())));

                table.addCell(new Cell().add(new Paragraph("性别").setFont(createPdfFont())));
                table.addCell(new Cell().add(new Paragraph("男").setFont(createPdfFont())));
            }else if (i == 1){
                // 第二行数据
                table.addCell(new Cell().add(new Paragraph("代表作").setFont(createPdfFont())));
                table.addCell(new Cell(1, 3).add(new Paragraph("《将进酒》《蜀道难》").setFont(createPdfFont())));
            }
        }

        return table;
    }

    /**
     * 创建段落
     * Paragraph 和 Text 关系，
     *  同一个设置如果 Text 存在，则以 Text 设置为显示方式
     *  如果 Text 没有设置，以 Paragraph 设置为显示方式
     *  对齐模式以 Paragraph 对齐模式设置为显示方式
     * @return
     * @throws IOException
     */
    public static Paragraph createParagraph() throws IOException {
        // 可以通过构造方法添加问题
        Paragraph paragraph = new Paragraph("段落内容");
        // 也可以通过添加 Text 对象添加文字
        paragraph.add(createText());
        // 段落加粗
        paragraph.setBold();
        // 段落设置字体
        paragraph.setFont(createPdfFont());
        // 段落设置下划
        paragraph.setUnderline();
        // 段落设置颜色
        paragraph.setFontColor(ColorConstants.RED);
        // 首行缩进
        paragraph.setFirstLineIndent(25);
        // 设置段落对齐模式，对齐模式以段落对齐模式设置而显示
        paragraph.setTextAlignment(TextAlignment.CENTER);

        return paragraph;
    }

    /**
     * 创建文本对象
     *
     * 注意要点：文本对象不能直接添加到document
     *
     * Paragraph 和 Text 关系，
     *  同一个设置如果 Text 存在，则以 Text 设置为显示方式
     *  如果 Text 没有设置，以 Paragraph 设置为显示方式
     * @return
     */
    public static Text createText() throws IOException {
        Text text = new Text("将进酒");
        // 字体
        text.setFont(createPdfFont());
        // 加粗
        text.setBold();
        // 字体颜色
        text.setFontColor(ColorConstants.BLACK);
        // 字体大小
        text.setFontSize(24);
        // 添加下划线
        text.setUnderline();
        // 设置文本对齐模式
        text.setTextAlignment(TextAlignment.LEFT);

        return text;
    }


    /**
     * 获取临时文件路径
     * @return
     */
    private static String getPdfFileName(){
        String userDir = System.getProperty("user.dir");
        String separator = System.getProperty("file.separator");
        return userDir + separator + "learn.pdf";
    }
}
