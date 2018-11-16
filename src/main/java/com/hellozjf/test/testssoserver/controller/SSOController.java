package com.hellozjf.test.testssoserver.controller;

import com.hellozjf.test.testssoserver.dataobject.Manufacturer;
import com.hellozjf.test.testssoserver.form.TokenQueryForm;
import com.hellozjf.test.testssoserver.repository.ManufacturerRepository;
import com.hellozjf.test.testssoserver.util.ResultUtils;
import com.hellozjf.test.testssoserver.util.UUIDUtils;
import com.hellozjf.test.testssoserver.vo.ResultVO;
import com.hellozjf.test.testssoserver.vo.TokenVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

/**
 * @author Jingfeng Zhou
 */
@Controller
@Slf4j
public class SSOController {

    @Autowired
    private ManufacturerRepository manufacturerRepository;

    /**
     * 申请接入
     *
     * @return
     */
    @RequestMapping("/apply")
    @ResponseBody
    public ResultVO apply(String zrrdah, String sfzjlxDm, String sfzjhm, String xm, String gjhdqszDm) {

        Manufacturer manufacturer = manufacturerRepository.findByZrrdah(zrrdah);
        if (manufacturer == null) {
            manufacturer = new Manufacturer();
            manufacturer.setSecret(UUIDUtils.uuid());
            manufacturer.setZrrdah(zrrdah);
            manufacturer.setSfzjlxDm(sfzjlxDm);
            manufacturer.setSfzjhm(sfzjhm);
            manufacturer.setXm(xm);
            manufacturer.setGjhdqszDm(gjhdqszDm);
            manufacturer = manufacturerRepository.save(manufacturer);
        }

        return ResultUtils.success(manufacturer);
    }

    @RequestMapping("/wtzzy")
    public ModelAndView wtzzy(@RequestParam("response_type") String responseType,
                              @RequestParam("client_id") String clientId,
                              @RequestParam("redirect_uri") String redirectUri,
                              @RequestParam("state") String state) {

        log.debug("enter wtzzy");

        if (! responseType.equals("code")) {
            log.error("responseType != code");
            return null;
        }

        Manufacturer manufacturer = manufacturerRepository.findById(clientId).orElse(null);
        if (manufacturer == null) {
            log.error("manufacturer == null");
            return null;
        }

        // 生成一个随机的code
        manufacturer.setCode(UUIDUtils.uuid());
        manufacturer.setRedirectUri(redirectUri);
        manufacturerRepository.save(manufacturer);

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(redirectUri);
        stringBuilder.append("?code=");
        stringBuilder.append(manufacturer.getCode());
        stringBuilder.append("&state=");
        stringBuilder.append(state);

        log.debug("redirect to {}", stringBuilder.toString());

        return new ModelAndView(new RedirectView(stringBuilder.toString()));
    }

    @PostMapping("/token/query")
    @ResponseBody
    public ResultVO tokenQuery(@RequestBody TokenQueryForm tokenQueryForm) {

        log.debug("enter tokenQuery");

        Manufacturer manufacturer = manufacturerRepository.findById(tokenQueryForm.getClientId()).orElse(null);
        if (manufacturer == null) {
            log.error("无此用户");
            return ResultUtils.error("INTERNAL_SERVER_ERROR ", "其他系统错误");
        }

        if (manufacturer.getCode().equals(tokenQueryForm.getCode()) &&
                manufacturer.getSecret().equals(tokenQueryForm.getClientSecret()) &&
                tokenQueryForm.getGrantType().equals("authorization_code") &&
                manufacturer.getRedirectUri().equals(tokenQueryForm.getRedirectUrl())) {
            // 操作
            TokenVO tokenVO = new TokenVO();
            manufacturer.setToken(UUIDUtils.uuid());
            manufacturer.setTokenExpireMsTime(System.currentTimeMillis() + 3600 * 1000);
            manufacturer.setCode("");
            manufacturerRepository.save(manufacturer);

            // 返回token
            tokenVO.setAccessToken(manufacturer.getToken());
            tokenVO.setExpiresIn(3600);
            return ResultUtils.success(tokenVO);
        } else {
            // 错误
            log.error("错误");
            return ResultUtils.error("INTERNAL_SERVER_ERROR ", "其他系统错误");
        }
    }

    @GetMapping("/userinfo/query")
    @ResponseBody
    public ResultVO userInfoQuery(String accessToken) {

        log.debug("enter userInfoQuery");

        Manufacturer manufacturer = manufacturerRepository.findByToken(accessToken);
        if (manufacturer == null || manufacturer.getTokenExpireMsTime() < System.currentTimeMillis()) {
            log.error("无效令牌");
            return ResultUtils.error("OAUTH2_INVALID_TOKEN ", "无效的 token");
        }

        return ResultUtils.success(manufacturer);
    }
}
