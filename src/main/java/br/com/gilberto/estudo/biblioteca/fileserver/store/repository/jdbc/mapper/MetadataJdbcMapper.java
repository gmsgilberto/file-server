package br.com.gilberto.estudo.biblioteca.fileserver.store.repository.jdbc.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import br.com.gilberto.estudo.biblioteca.fileserver.store.model.Metadata;

@Component
public class MetadataJdbcMapper implements RowMapper<Metadata> {


	public Metadata map(ResultSet rs) throws SQLException{
		return mapRow(rs, 1);
	}

	@Override
	public Metadata mapRow(ResultSet rs, int rowNum) throws SQLException {
		
		return Metadata
				.builder()
					.documentId(rs.getString("DOCUMENT_ID"))
					.fileName(rs.getString("FILE_NAME"))
					.originalFileName(rs.getString("ORIGINAL_FILE_NAME"))
					.processid(rs.getString("PROCESS_ID"))
					.creationTime(rs.getTimestamp("CREATED_DATE"))
					.size(rs.getInt("BYTES"))
					.sucess(rs.getString("FLAG_PERSISTED").equalsIgnoreCase("S"))
				.build();
	}

}
