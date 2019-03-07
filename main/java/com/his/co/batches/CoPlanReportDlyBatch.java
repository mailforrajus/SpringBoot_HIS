package com.his.co.batches;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import com.his.co.model.CoPdfModel;
import com.his.co.model.CoTriggersModel;
import com.his.co.service.CoTriggersService;
import com.his.ed.model.EligibilityDetailModel;
import com.his.ed.service.EligibilityDetailService;
import com.his.util.AppConstants;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

@Service("batch")
public class CoPlanReportDlyBatch extends CoAbstractBatch {

	private static final String BATH_NAME = "PLAN_RPRT_DLY";

	private static Integer TOTAL_TRGS = 0;
	private static Integer SUCCESS_TRGS = 0;
	private static Integer FAILURE_TRGS = 0;

	public void init() {
		preProcess(BATH_NAME);
		start();
		postProcess(BATH_NAME, TOTAL_TRGS, SUCCESS_TRGS, FAILURE_TRGS);
	}

	/*
	 * public static void main(String[] args) { CoPlanReportDlyBatch crdb = new
	 * CoPlanReportDlyBatch(); crdb.init(); }
	 */

	private static final String FILE_NAME = "E:\\AshokAK\\HealthInsurance\\plan_ap.pdf";

	@Autowired
	private CoTriggersService coTrgService;

	@Autowired
	private EligibilityDetailService edService;

	/**
	 * This method is used to process each trigger
	 */
	@Override
	public void process(CoTriggersModel coTrgModel)  {
		EligibilityDetailModel edModel = edService.findByCaseNum(coTrgModel.getCaseNum());

		if (null != edModel) {
			String planStatus = edModel.getPlanStatus();

			if (planStatus.equals(AppConstants.CASE_PLAN_STATUS)) {
				// generate approved pdf
				try {
					buildPlanApPdf(edModel);
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (DocumentException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} else {
				// generate denied pdf
				System.out.println("denied");
				try {
					buildPlanDnPdf(edModel);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			// store that pdf in db
			storePdf(edModel);
			// send that pdf to email
			updatePendingTrigger(coTrgModel);
		}

	}

	private void updatePendingTrigger(CoTriggersModel coTrgModel) {
		coTrgModel.setTriggerStatus("C");
		coTrgService.updatePendingTrigger(coTrgModel);
	}

	@Override
	public void start() {
		ExecutorService service=null;
		CompletionService<String> pool=null;
		List<CoTriggersModel> models = coTrgService.findPendingTriggers();
		if (!models.isEmpty()) {
			TOTAL_TRGS = models.size();
			service=Executors.newFixedThreadPool(40);
			pool= new ExecutorCompletionService<String>(service);
			
			
			// processing each trigger
			for (CoTriggersModel model : models) {
				try {
					/*pool.submit(new Callable<String>() {
						
						@Override
						public String call() throws Exception {
							// TODO Auto-generated method stub*/
							process(model);
							SUCCESS_TRGS++;
							/*return null;
						}
					});*/
					
				} catch (Exception e) {
					FAILURE_TRGS++;
					e.printStackTrace();
				}
			}
		} else {
			System.exit(0);
		}
	}

	/**
	 * This method is used to create pdf with plan details
	 * 
	 * @param edModel
	 * @throws FileNotFoundException
	 * @throws DocumentException
	 */
	public void buildPlanApPdf(EligibilityDetailModel edModel) throws FileNotFoundException, DocumentException {

		Document document = new Document();
		PdfWriter.getInstance(document, new FileOutputStream(edModel.getCaseNum().toString()+".pdf"));

		// open document
		document.open();

		// Creating paragraph
		Paragraph p = new Paragraph();
		p.add("Approved Plan Details");
		p.setAlignment(Element.ALIGN_CENTER);

		// adding paragraph to document
		document.add(p);

		// Create Table object, Here 2 specify the no. of columns
		PdfPTable pdfPTable = new PdfPTable(2);

		// First row in table
		PdfPCell pdfPCell1 = new PdfPCell(new Paragraph(edModel.getCaseNum().toString()+".pdf"));
		PdfPCell pdfPCell2 = new PdfPCell(new Paragraph(edModel.getCaseNum().toString()));

		// Add cells to table
		pdfPTable.addCell(pdfPCell1);
		pdfPTable.addCell(pdfPCell2);

		// Second row in table
		PdfPCell pdfPCell3 = new PdfPCell(new Paragraph("Plan Name"));
		PdfPCell pdfPCell4 = new PdfPCell(new Paragraph(edModel.getPlanName().toString()));

		// Add cells to table
		pdfPTable.addCell(pdfPCell3);
		pdfPTable.addCell(pdfPCell4);

		// Third Row
		pdfPTable.addCell(new PdfPCell(new Paragraph("Plan Status")));
		pdfPTable.addCell(new PdfPCell(new Paragraph(edModel.getPlanStatus())));

		// Fourth Row
		pdfPTable.addCell(new PdfPCell(new Paragraph("Plan Start Date")));
		pdfPTable.addCell(new PdfPCell(new Paragraph(edModel.getPlanStartDate())));

		// Fifth Row
		pdfPTable.addCell(new PdfPCell(new Paragraph("Plan End Date")));
		pdfPTable.addCell(new PdfPCell(new Paragraph(edModel.getPlanEndDate())));

		// sixth Row
		pdfPTable.addCell(new PdfPCell(new Paragraph("Benfit Amount")));
		pdfPTable.addCell(new PdfPCell(new Paragraph(edModel.getBenefitAmt())));

		// Add content to the document using Table objects.

		document.add(pdfPTable);
		document.close();
	}

	/**
	 * This method is used to create with denied plan details
	 * 
	 * @param edModel
	 */
	public void buildPlanDnPdf(EligibilityDetailModel edModel) throws Exception {

		Document document = new Document();
		PdfWriter.getInstance(document, new FileOutputStream(edModel.getCaseNum().toString()+".pdf"));

		// open document
		document.open();

		// Creating paragraph
		Paragraph p = new Paragraph();
		p.add("Denied Plan Details");
		p.setAlignment(Element.ALIGN_CENTER);

		// adding paragraph to document
		document.add(p);

		// Create Table object, Here 2 specify the no. of columns
		PdfPTable pdfPTable = new PdfPTable(2);

		// First row in table
		PdfPCell pdfPCell1 = new PdfPCell(new Paragraph("Case Number"));
		PdfPCell pdfPCell2 = new PdfPCell(new Paragraph(edModel.getCaseNum().toString()));

		// Add cells to table
		pdfPTable.addCell(pdfPCell1);
		pdfPTable.addCell(pdfPCell2);

		// Second row in table
		PdfPCell pdfPCell3 = new PdfPCell(new Paragraph("Plan Name"));
		PdfPCell pdfPCell4 = new PdfPCell(new Paragraph(edModel.getPlanName().toString()));

		// Add cells to table
		pdfPTable.addCell(pdfPCell3);
		pdfPTable.addCell(pdfPCell4);

		// Third Row
		pdfPTable.addCell(new PdfPCell(new Paragraph("Plan Status")));
		pdfPTable.addCell(new PdfPCell(new Paragraph(edModel.getPlanStatus())));

		// Fourth Row
		pdfPTable.addCell(new PdfPCell(new Paragraph("Denial Reason")));
		pdfPTable.addCell(new PdfPCell(new Paragraph(edModel.getDenialReason())));

		// Add content to the document using Table objects.

		document.add(pdfPTable);
		document.close();

	}

	/**
	 * This is used to fetch dummy data
	 * 
	 * @return
	 */
	public EligibilityDetailModel getEdData() {
		EligibilityDetailModel model = new EligibilityDetailModel();
		model.setCaseNum(78956l);
		model.setPlanName("SNAP");
		model.setPlanStartDate("18-Feb-2019");
		model.setPlanEndDate("18-March-2019");
		model.setPlanStatus("AP");
		model.setBenefitAmt("$350.00");
		return model;
	}

	
	public String storePdf(EligibilityDetailModel model) {
		CoPdfModel pdfModel=null;
		byte[] casePdf=null;
		FileInputStream pdfFile=null;
		pdfModel=new CoPdfModel ();
		try {
			pdfFile = new FileInputStream(model.getCaseNum().toString()+".pdf");
			casePdf=toByteArrayUsingJava(pdfFile);
		} catch (IOException e) {
			e.printStackTrace();
		}
		pdfModel.setCaseNumber(model.getCaseNum());
		pdfModel.setPlanName(model.getPlanName());
		pdfModel.setPlanStatus(model.getPlanStatus());
		pdfModel.setPdfDocument(casePdf);
		//call service class method
		coTrgService.savePdf(pdfModel);
		return model.getCaseNum().toString();
	}
	
	public byte[] toByteArrayUsingJava(InputStream is)
		    throws IOException{
		        ByteArrayOutputStream baos = new ByteArrayOutputStream();
		        int reads = is.read();
		       
		        while(reads != -1){
		            baos.write(reads);
		            reads = is.read();
		        }
		      
		        return baos.toByteArray();
		       
		    }
}
