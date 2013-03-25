package org.primefaces.examples.view;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

public class FileUploadController {

    private UploadedFile file;

    public UploadedFile getFile() {
        return file;
    }

    public void setFile(UploadedFile file) {
        this.file = file;
    }

    public void upload() {
        if(file != null) {
            FacesMessage msg = new FacesMessage("Succesful", file.getFileName() + " is uploaded.");
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }
    }

    public void handleFileUpload(FileUploadEvent event) {
		FacesMessage msg = new FacesMessage("Succesful", event.getFile().getFileName() + " is uploaded.");
		FacesContext.getCurrentInstance().addMessage(null, msg);
		
		OutputStream out=null;
		InputStream fileContent=null;
		try{
			UploadedFile uploadedFile= event.getFile();
			out= new FileOutputStream(new File("D:/")+uploadedFile.getFileName());
			fileContent= uploadedFile.getInputstream();
			int read=0;
			final byte[] bytes= new byte[1024];
			while((read= fileContent.read(bytes))!=-1){
			   out.write(bytes,0,read);	
			}
		}
		catch(FileNotFoundException fnfe){
			
		}
		catch(IOException ioex){
			
		}
		finally{
			if(out!=null){
				try {
					out.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if(fileContent!=null){
				try {
					fileContent.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
		}
	}
}