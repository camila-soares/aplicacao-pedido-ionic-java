package com.camilasoares.cursomc.services;

import org.springframework.stereotype.Service;

@Service
public class S3Service {


//
//	private Logger LOG = LoggerFactory.getLogger(S3Service.class);
//
//	@Autowired
//	private AmazonS3 s3client;
//
//	@Value("${s3.bucket}")
//	private String bucketName;
//
//	public URI uploadFile(MultipartFile multipartFile) {
//		try {
//			String fileName = multipartFile.getOriginalFilename();
//			InputStream is = multipartFile.getInputStream();
//			String contentType = multipartFile.getContentType();
//			return uploadFile(is, fileName, contentType);
//		} catch (IOException e) {
//			throw new FilerException("Erro de IO: " + e.getMessage());
//		}
//	}
//
//	public URI uploadFile(InputStream is, String fileName, String contentType) {
//		try {
//			ObjectMetadata meta = new ObjectMetadata();
//			meta.setContentType(contentType);
//			LOG.info("Iniciando upload");
//			s3client.putObject(bucketName, fileName, is, meta);
//			LOG.info("Upload finalizado");
//			return s3client.getUrl(bucketName, fileName).toURI();
//		} catch (URISyntaxException e) {
//			throw new FileException("Erro ao converter URL para URI");
//		}
//	}
//}

}
