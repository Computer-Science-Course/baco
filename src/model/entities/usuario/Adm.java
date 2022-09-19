package model.entities.usuario;

import java.time.LocalDateTime;

import model.enums.TipoDocumento;

public class Adm extends Usuario{

	public Adm(
			String numero_documento, TipoDocumento tipo_documento, String nome_completo, String senha, Boolean isAdm,
			LocalDateTime ultimo_acesso
	) {
		super(numero_documento, tipo_documento, nome_completo, senha, isAdm, ultimo_acesso);
	}

}
