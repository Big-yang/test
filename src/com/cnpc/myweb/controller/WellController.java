package com.cnpc.myweb.controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.cnpc.myweb.domain.FusionCharts;
import com.cnpc.myweb.domain.Well;
import com.cnpc.myweb.services.WellService;

@Controller
@RequestMapping(value = "/well.do")
public class WellController {
	
	@Autowired
	private WellService genWellProInfo;		
	
	@RequestMapping(params = "method=getwellinfo")
	public ModelAndView getWellInfo(HttpServletRequest request,HttpServletResponse response,ModelMap modelMap) throws Exception{
		
		ModelAndView mav = new ModelAndView();
		List<Well> wellList = genWellProInfo.getWellList();
		mav.addObject("wellinfo", wellList);
		mav.setViewName("well/wellinfo");
		return mav;
	}
	@RequestMapping(params = "method=getjsonwellinfo")
	public @ResponseBody Object getJsonWellInfo(HttpServletRequest request,HttpServletResponse response,ModelMap modelMap) throws Exception{
		String path=request.getContextPath();
		System.out.println(path);
		List<Well> wellList = genWellProInfo.getWellList();
		
		System.out.println("getJsonWellInfo() run...........................");
		
		return wellList;
	}
	@RequestMapping(params = "method=getCharData")
	public @ResponseBody Object getCharData(HttpServletRequest request,HttpServletResponse response,ModelMap modelMap) throws Exception{
	/*	String path=request.getContextPath();
		System.out.println(path);*/
		List<Well> wellList = genWellProInfo.getWellList();
		/*System.out.println("getchardata() run...........................");*/
		List<FusionCharts> fc=new ArrayList<FusionCharts>();
		if(wellList.size()!=0){
			for(Well w:wellList){
				String label=w.getName();
				Double value=(double) w.getValue();
				fc.add(new FusionCharts(label,value));
			}
		}
		else  
			fc.add(new FusionCharts("",0.0));
		return fc;
		
	}
	/*@RequestMapping(params = "method=getCharData")
	public @ResponseBody 
	List<FusionCharts> getCharData(HttpServletRequest request){
		List<Well> wellList = genWellProInfo.getWellList();
		List<FusionCharts> fc=new ArrayList<FusionCharts>();
		if(wellList.size()!=0){
			for(Well w:wellList){
				String label=w.getName();
				Double value=(double) w.getValue();
				fc.add(new FusionCharts(label,value));
			}
		}
		else  
			fc.add(new FusionCharts("",0.0));
		return fc;
		
		
	}*/
	
	@RequestMapping(params = "method=exportExcel")
	public void exportExcel(HttpServletRequest request,HttpServletResponse response){
		String fileName = request.getParameter("fileName");
		List<Well> wellList = genWellProInfo.getWellList();
		HSSFWorkbook hssWorkbook = new HSSFWorkbook();
		HSSFSheet sheet = hssWorkbook.createSheet("多井产量对比");
		HSSFCellStyle centerGrayStyle = hssWorkbook.createCellStyle();//创建一个样式
		centerGrayStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		centerGrayStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		centerGrayStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);
		centerGrayStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);
		centerGrayStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		centerGrayStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		HSSFCellStyle headStyle= hssWorkbook.createCellStyle();
		headStyle.cloneStyleFrom(centerGrayStyle);
		HSSFFont blodfont=hssWorkbook.createFont();
				         blodfont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
				         headStyle.setFont(blodfont);
		sheet.createFreezePane(0,1);//冻结(列,行)区域中的单元格
		sheet.setDefaultColumnWidth(22);
		HSSFRow header = sheet.createRow(0);
		HSSFCell h0=header.createCell(0);
				 h0.setCellValue("井名称");
		h0.setCellStyle(headStyle);
		HSSFCell h1=header.createCell(1);
				h1.setCellValue("单井日产量");
				h1.setCellStyle(headStyle);
		int rowNum=1;
		for(Well w:wellList){
			String label=w.getName();
			Integer value=w.getValue();
			HSSFRow row = sheet.createRow(rowNum++);
			row.createCell(0).setCellValue(label);
			
			row.createCell(1).setCellValue(value);
			
		}
		
		try {
			fileName = java.net.URLDecoder.decode(fileName, "UTF-8");
//			fileName = java.net.URLEncoder.encode(fileName,"utf-8");
			fileName = new String(fileName.getBytes("utf-8"),"iso-8859-1");
			response.reset();
			response.setContentType("application/x-msdownload");
			response.setHeader("Content-disposition","attachment;fileName="+fileName+".xls");
			ServletOutputStream sos = response.getOutputStream();
			hssWorkbook.write(sos);
			sos.flush();
			sos.close();
			
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch(IOException e){
			e.printStackTrace();
//			logger.error(e);
		}
		
	}
	

}

