package com.shandong.culture.manager.web;

import javax.validation.Valid;

import org.apache.commons.lang3.StringUtils;
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
import com.dm.common.exception.DataValidateException;
import com.shandong.culture.manager.converter.MemberConverter;
import com.shandong.culture.manager.dto.MemberDto;
import com.shandong.culture.manager.dto.RegistryDto;
import com.shandong.culture.manager.service.MemberService;

@RestController
@RequestMapping("members")
public class MemberController {

	@Autowired
	private MemberService memberService;

	@Autowired
	private MemberConverter memberConverter;

	@PostMapping
	public MemberDto save(@RequestBody MemberDto data) {
		return memberConverter.toDto(memberService.save(data));
	}

	@GetMapping("{id}")
	public MemberDto get(@PathVariable("id") Long id) {
		return memberConverter.toDto(memberService.findById(id));
	}

	@GetMapping(params = { "draw" })
	public TableResult<MemberDto> search(@RequestParam("draw") Long draw,
			@RequestParam(value = "keywords", required = false) String keywords,
			@PageableDefault(page = 0, size = 10) Pageable pageable) {
		return TableResult.success(draw, memberService.find(keywords, pageable), memberConverter::toDto);
	}

	/**
	 * 注册
	 * 
	 * @param data
	 * @return
	 */
	@PostMapping("registry")
	public MemberDto registry(@RequestBody @Valid RegistryDto data) {
		validationRepassword(data);
		MemberDto member = memberConverter.toDto(data);
		member.setType("个人");// 默认用户类型为个人用户
		return memberConverter.toDto(memberService.save(member));
	}

	@GetMapping("userVerification")
	public Boolean usernameCheck(@RequestParam String username) {
		return memberService.existByUsername(username);
	}

	private void validationRepassword(RegistryDto data) {
		if (!StringUtils.equals(data.getPassword(), data.getRepassword())) {
			throw new DataValidateException();
		}
	}

}
