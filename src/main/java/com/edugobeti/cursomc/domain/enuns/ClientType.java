package com.edugobeti.cursomc.domain.enuns;

public enum ClientType {
	
	PESSOAFISICA(1, "Pessoa Física"),
	PESSSOJURIDICA(2, "Pessoa Jurídica");
	
	int cod;
	String desc;
	
	private ClientType(int cod, String desc){
		this.cod = cod;
		this.desc = desc;
	}

	public int getCod() {
		return cod;
	}

	public String getDesc() {
		return desc;
	}
	
	public static ClientType toEnum(Integer id) {
		if(id == null) {
			return null;
		}
		for (ClientType x : ClientType.values()) {
			if(id.equals(x.getCod())){
				return x;
			}
		}
		throw new IllegalArgumentException("Id inválido: " + id);
			
	}
	

}
