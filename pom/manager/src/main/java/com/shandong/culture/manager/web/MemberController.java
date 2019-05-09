package com.shandong.culture.manager.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.dm.common.dto.TableResult;
import com.shandong.culture.manager.converter.MemberConverter;
import com.shandong.culture.manager.dto.MemberDto;
import com.shandong.culture.manager.entity.Member;
import com.shandong.culture.manager.service.MemberService;

@RestController
@RequestMapping("members")
public class MemberController {

	@Autowired
	private MemberService memberService;

	private MemberConverter memberConverter;

	@PostMapping
	public MemberDto save(@RequestBody MemberDto data) {
		return memberConverter.toDto(memberService.save(data));
	}

	@GetMapping("{id}")
	public Member get(@PathVariable("id") Long id) {
		return memberService.findById(id);
	}

	@GetMapping(params = { "draw" })
	public TableResult<Member> search(@RequestParam("draw") Long draw,
			@RequestParam(value = "keywords", required = false) String keywords,
			@PageableDefault(page = 0, size = 10) Pageable pageable) {
		return TableResult.success(draw, memberService.find(keywords, pageable));
	}
}
