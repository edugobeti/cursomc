package com.edugobeti.cursomc.domain.enuns;

public enum PaymentStatus {
	
	PENDENTE(1, "Pendente"),
	QUITADO(2, "Quitado"),
	PARCELADO(1, "Parcelado");
	
	private int cod;
	private String desc;
	
	private PaymentStatus(int cod, String desc) {
		this.cod = cod;
		this.desc = desc;
	}
	
	public int getCod() {
		return cod;
	}

	public String getDesc() {
		return desc;
	}
	
	public static PaymentStatus  toEnum(Integer id) {
		if(id == null) {
			return null;
		}
		for (PaymentStatus x : PaymentStatus.values()) {
			if(id.equals(x.getCod())){
				return x;
			}
		}
		throw new IllegalArgumentException("Id inválido: " + id);
			
	}
	
}
