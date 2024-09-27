package br.com.gilberto.estudo.biblioteca.fileserver.store.repository.jdbc;

import java.sql.ResultSet;

import org.springframework.jdbc.support.JdbcUtils;
import org.springframework.stereotype.Repository;

import br.com.gilberto.estudo.biblioteca.fileserver.exceptions.FileServerException;
import br.com.gilberto.estudo.biblioteca.fileserver.store.model.Metadata;
import br.com.gilberto.estudo.biblioteca.fileserver.store.repository.MetadataRepository;
import br.com.gilberto.estudo.biblioteca.fileserver.store.repository.jdbc.mapper.MetadataJdbcMapper;
import lombok.AllArgsConstructor;

@Repository
@AllArgsConstructor
public class MetadataRepositoryJdbc implements MetadataRepository {

	private final javax.sql.DataSource dataSource;
	private MetadataJdbcMapper mapper;
	
	@Override
	public Metadata create(Metadata metadata) {
		
		var sql = new StringBuilder();
		sql.append("\n INSERT ");
		sql.append("\n   INTO FILE_METADATA(");
		sql.append("\n     DOCUMENT_ID,");
		sql.append("\n     FILE_NAME,");
		sql.append("\n     ORIGINAL_FILE_NAME,");
		sql.append("\n     PROCESS_ID,");
		sql.append("\n     CREATED_DATE,");
		sql.append("\n     BYTES,");
		sql.append("\n     FLAG_PERSISTED");
		sql.append("\n   )");
		sql.append("\n VALUES (?,?,?,?,?,?,?) ");
		
		try( var conn = this.dataSource.getConnection();
			 var pstmt = conn.prepareStatement(sql.toString());
				){
			
			var index = 0;
			pstmt.setString(++index, metadata.getDocumentId());
			pstmt.setString(++index, metadata.getFileName());
			pstmt.setString(++index, metadata.getOriginalFileName());
			pstmt.setString(++index, metadata.getProcessid());
			pstmt.setTimestamp(++index, new java.sql.Timestamp(metadata.getCreationTime().getTime()));
			pstmt.setInt(++index, metadata.getSize());
			pstmt.setString(++index, metadata.isSucess() ? "S" : "N");
			pstmt.executeUpdate();
		
			return metadata;
			
		}catch (Exception e) {
			throw new FileServerException("Nao foi possivel persistir o metada " + metadata, e);
		}
		
	}

	@Override
	public Metadata update(Metadata metadata) {
		
		var sql = new StringBuilder();
		sql.append("\n UPDATE FILE_METADATA");
		sql.append("\n     SET FLAG_PERSISTED = ? ");
		sql.append("\n  WHERE DOCUMENT_ID     = ? ");
		
		try( var conn = this.dataSource.getConnection();
			 var pstmt = conn.prepareStatement(sql.toString());
				){
			
			var index = 0;
			pstmt.setString(++index, metadata.isSucess() ? "S" : "N");
			pstmt.setString(++index, metadata.getDocumentId());
			pstmt.executeUpdate();
		
			return metadata;
			
		}catch (Exception e) {
			throw new FileServerException("Nao foi possivel fazer o updade do metada " + metadata, e);
		}
				
	}

	@Override
	public Metadata findById(String documentid) {
		
		var sql = new StringBuilder();
		sql.append("\n SELECT *");
		sql.append("\n   FROM FILE_METADATA");
		sql.append("\n  WHERE DOCUMENT_ID = ?");
		
		ResultSet rs = null;
		try( var conn = this.dataSource.getConnection();
			 var pstmt = conn.prepareStatement(sql.toString());
				){
			
			var index = 0;
			pstmt.setString(++index, documentid);
			rs = pstmt.executeQuery();
		
			return rs.next() ?  this.mapper.map(rs) : null;
			
		}catch (Exception e) {
			throw new FileServerException("Nao foi possivel consultar o documento " + documentid, e);
		}finally {
			JdbcUtils.closeResultSet(rs);
		}
					
		
	}

}
