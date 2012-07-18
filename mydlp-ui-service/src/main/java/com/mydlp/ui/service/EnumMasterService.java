package com.mydlp.ui.service;



public interface EnumMasterService {

	public abstract class EnumJob {
		public abstract void enumerateNow();
		
		public boolean equals(Object obj) {
			if (! (obj instanceof EnumJob))
				return false;
			return toString().equals(((EnumJob)obj).toString());
		}
		
		public int hashCode() {
			return toString().hashCode();
		}
		
		public abstract String toString();
	}
	
	public void schedule(EnumJob enumJob);

}
