package com.pradeya.cast.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.pradeya.cast.domain.Media;
import com.pradeya.cast.repository.MediaRepository;
import com.pradeya.cast.repository.SequenceRepository;

@Service
public class MediaService {
	
	public static final String MEDIA = "media";
	
	@Autowired
	private MediaRepository mediaRepository;
	
	@Autowired
	private SequenceRepository sequenceRepository;
	
	
	public Iterable<Media> findAll(){
		return mediaRepository.findAll();
	}
	
	public Iterable<Media> findUserMediaByUserName(String userName) {
		return mediaRepository.findByUserNameSortCDate(userName, new Sort(Sort.Direction.DESC,"creationTime"));
		
	}
	
	public Media create(Media media) {
		media.setId(sequenceRepository.getNextSequence(MEDIA));
		return mediaRepository.save(media);
	}

	public Media update(Media media){
		return mediaRepository.save(media);
	}

	public Media findMedia(long mediaId){
		return mediaRepository.findOne(mediaId);
	}

}
