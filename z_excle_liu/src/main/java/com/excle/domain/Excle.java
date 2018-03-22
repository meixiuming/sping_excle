package com.excle.domain;


import java.io.Serializable;


public class Excle implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	  	private int id;
	  	private String NO;
		private String smt;
	    private String ffo;
	    private String partName;
	    private String reqmTNo;

	    private String procedureNo;
	    private String procedureTitle;
	    private String reg;
	    
	  
	    public Excle() {
			super();
		}

		public int getId() {
	        return id;
	    }

	    public void setId(int id) {
	        this.id = id;
	    }
	    public String getNO() {
			return NO;
		}

		public void setNO(String nO) {
			NO = nO;
		}

	   
	    public String getSmt() {
	        return smt;
	    }

	    public void setSmt(String smt) {
	        this.smt = smt;
	    }

	   
	    public String getFfo() {
	        return ffo;
	    }

	    public void setFfo(String ffo) {
	        this.ffo = ffo;
	    }

	    public String getPartName() {
	        return partName;
	    }

	    public void setPartName(String partName) {
	        this.partName = partName;
	    }

	  
	    public String getReqmTNo() {
	        return reqmTNo;
	    }

	    public void setReqmTNo(String reqmTNo) {
	        this.reqmTNo = reqmTNo;
	    }

	  
	

	   
	    public String getProcedureNo() {
	        return procedureNo;
	    }

	    public void setProcedureNo(String procedureNo) {
	        this.procedureNo = procedureNo;
	    }

	  
	    public String getProcedureTitle() {
	        return procedureTitle;
	    }

	    public void setProcedureTitle(String procedureTitle) {
	        this.procedureTitle = procedureTitle;
	    }

	   
	    public String getReg() {
	        return reg;
	    }

	    public void setReg(String reg) {
	        this.reg = reg;
	    }

	    @Override
	    public boolean equals(Object o) {
	        if (this == o) return true;
	        if (o == null || getClass() != o.getClass()) return false;

	        Excle that = (Excle) o;

	        if (id != that.id) return false;
	        if (smt != null ? !smt.equals(that.smt) : that.smt != null) return false;
	        if (ffo != null ? !ffo.equals(that.ffo) : that.ffo != null) return false;
	        if (partName != null ? !partName.equals(that.partName) : that.partName != null) return false;
	        if (reqmTNo != null ? !reqmTNo.equals(that.reqmTNo) : that.reqmTNo != null)
	            return false;
//	        if (reqmTTitle != null ? !reqmTTitle.equals(that.reqmTTitle) : that.reqmTTitle != null) return false;
	        if (procedureNo != null ? !procedureNo.equals(that.procedureNo) : that.procedureNo != null) return false;
	        if (procedureTitle != null ? !procedureTitle.equals(that.procedureTitle) : that.procedureTitle != null)
	            return false;
	        if (reg != null ? !reg.equals(that.reg) : that.reg != null) return false;

	        return true;
	    }

	    @Override
	    public int hashCode() {
	        int result = id;
	        result = 31 * result + (smt != null ? smt.hashCode() : 0);
	        result = 31 * result + (ffo != null ? ffo.hashCode() : 0);
	        result = 31 * result + (partName != null ? partName.hashCode() : 0);
	        result = 31 * result + (reqmTNo != null ? reqmTNo.hashCode() : 0);
//	        result = 31 * result + (reqmTTitle != null ? reqmTTitle.hashCode() : 0);
	        result = 31 * result + (procedureNo != null ? procedureNo.hashCode() : 0);
	        result = 31 * result + (procedureTitle != null ? procedureTitle.hashCode() : 0);
	        result = 31 * result + (reg != null ? reg.hashCode() : 0);
	        return result;
	    }

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	

}
