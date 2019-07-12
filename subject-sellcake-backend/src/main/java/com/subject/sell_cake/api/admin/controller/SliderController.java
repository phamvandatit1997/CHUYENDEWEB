package com.subject.sell_cake.api.admin.controller;

import com.subject.sell_cake.api.AbstractBasedAPI;
import com.subject.sell_cake.api.admin.request.PagingRequestModel;
import com.subject.sell_cake.api.admin.request.SliderRequest;
import com.subject.sell_cake.auth.AuthUser;
import com.subject.sell_cake.exception.ApplicationException;
import com.subject.sell_cake.model.RestAPIResponse;
import com.subject.sell_cake.model.entity.Slider;
import com.subject.sell_cake.response.APIStatus;
import com.subject.sell_cake.service.FileUploadService;
import com.subject.sell_cake.service.SliderService;
import com.subject.sell_cake.util.CommonUtil;
import com.subject.sell_cake.util.Constant;
import com.subject.sell_cake.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

@RestController
@RequestMapping(path = Constant.SLIDER)
public class SliderController extends AbstractBasedAPI {

    @Autowired
    SliderService sliderService;

    @Autowired
    FileUploadService fileUploadService;

    // create slider
    @RequestMapping(path = Constant.SLIDER_CREATE, method = RequestMethod.POST)
    public ResponseEntity<RestAPIResponse> createSlider(
            HttpServletRequest request,
            @RequestPart(value = "images", required = false) MultipartFile fileName,
            @RequestPart(value = "slider", required = false) SliderRequest sliderRequest
            ) {
        AuthUser authUser = getAuthUserFromSession(request);
        Date currentDate = new Date();
        if (authUser != null) {
            Slider newSlider = new Slider();
            newSlider.setCreateDate(DateUtil.convertToUTC(currentDate));
            newSlider.setLastActivity(DateUtil.convertToUTC(currentDate));
            newSlider.setStatus(Constant.Status.ACTIVE.getValues());
            if (fileName != null) {
                String fileNames = "slider_" + CommonUtil.generateSalt() + CommonUtil.getFileExtension(fileName);

                String url = fileUploadService.uploadFile(fileName, fileNames);

                String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                        .path("/files/")
                        .path(fileNames)
                        .toUriString();
                newSlider.setSlideLinks(fileDownloadUri);
            } else if(sliderRequest.getSlideImage() != null) {
                newSlider.setSlideLinks(sliderRequest.getSlideImage());
            }
            return responseUtil.successResponse(sliderService.saveSlider(newSlider));
        }
        throw new ApplicationException(APIStatus.ERR_USER_NOT_FOUND);
    }
    // paging search slider
    @RequestMapping(path = Constant.SLIDER_GET_LIST, method = RequestMethod.POST)
    public ResponseEntity<RestAPIResponse> pagingBoxSlider(HttpServletRequest request, @RequestBody PagingRequestModel pagingRequestModel) {
        AuthUser authUser = getAuthUserFromSession(request);
        if (authUser != null) {
            Page<Slider> pagingBoxSlider = sliderService.pagingBoxSlider(pagingRequestModel, authUser.getLang());
            return responseUtil.successResponse(pagingBoxSlider);
        }
        throw new ApplicationException(APIStatus.ERR_USER_NOT_FOUND);
    }

    // paging search slider client
    @RequestMapping(path = Constant.SLIDER_CLIENT_LIST, method = RequestMethod.POST)
    public ResponseEntity<RestAPIResponse> pagingBoxSliderClient(HttpServletRequest request, @RequestBody PagingRequestModel pagingRequestModel) {
        Page<Slider> pagingBoxSlider = sliderService.pagingBoxSlider(pagingRequestModel,"vi");
        return responseUtil.successResponse(pagingBoxSlider);
    }

    // change status
    @RequestMapping(path = Constant.SLIDER_CHANGE_STATUS, method = RequestMethod.PUT)
    public ResponseEntity<RestAPIResponse> changeStatusSlider(HttpServletRequest request, @Param(value = "slider_id") String slider_id) {
        AuthUser authUser = getAuthUserFromSession(request);
        if (authUser != null) {
            Slider slider = sliderService.findSliderBySliderId(slider_id);
            if (slider != null) {
                if (slider.getStatus() == Constant.Status.ACTIVE.getValues()) {
                    slider.setStatus(Constant.Status.DELETE.getValues());
                    return responseUtil.successResponse(sliderService.saveSlider(slider));
                } else {
                    slider.setStatus(Constant.Status.ACTIVE.getValues());
                    return responseUtil.successResponse(sliderService.saveSlider(slider));
                }
            } else {
                throw new ApplicationException(APIStatus.ERR_SLIDER_NOT_FOUND);
            }
        }
        throw new ApplicationException(APIStatus.ERR_USER_NOT_FOUND);
    }
}