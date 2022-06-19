package tk.monkeycode.blogapi.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import tk.monkeycode.blogapi.dto.TagsResponse;
import tk.monkeycode.blogapi.repository.TagRepository;
import tk.monkeycode.blogapi.service.TagService;

@Service
@RequiredArgsConstructor
public class TagServiceImpl implements TagService {
	
	private final TagRepository tagRepository;

	@Override
	public TagsResponse findAll() {
		List<String> tagNames = tagRepository.findAll().stream().map(tag -> tag.getName()).collect(Collectors.toList());
		return new TagsResponse(tagNames);
	}
	
}
