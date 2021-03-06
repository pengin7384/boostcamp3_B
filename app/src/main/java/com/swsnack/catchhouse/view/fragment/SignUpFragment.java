package com.swsnack.catchhouse.view.fragment;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.swsnack.catchhouse.R;
import com.swsnack.catchhouse.databinding.FragmentSignUpBinding;
import com.swsnack.catchhouse.util.KeyboardUtil;
import com.swsnack.catchhouse.view.BaseFragment;
import com.swsnack.catchhouse.viewmodel.userviewmodel.UserViewModel;
import com.yalantis.ucrop.UCrop;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import static android.app.Activity.RESULT_OK;
import static com.facebook.FacebookSdk.getCacheDir;
import static com.swsnack.catchhouse.Constant.RequestCode.GALLERY;
import static com.swsnack.catchhouse.Constant.Ucrop.UCROP_HEIGHT_MAX;
import static com.swsnack.catchhouse.Constant.Ucrop.UCROP_SQUARE;
import static com.swsnack.catchhouse.Constant.Ucrop.UCROP_WIDTH_MAX;

public class SignUpFragment extends BaseFragment<FragmentSignUpBinding, UserViewModel> {

    @Override
    protected int getLayout() {
        return R.layout.fragment_sign_up;
    }

    @Override
    protected Class<UserViewModel> getViewModelClass() {
        return UserViewModel.class;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        getBinding().tbSignIn.setNavigationIcon(R.drawable.back_button_primary);
        getBinding().tbSignIn.setNavigationOnClickListener(__ -> getActivity().getSupportFragmentManager().popBackStack());
        return getBinding().getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        getBinding().setHandler(getViewModel());
        getBinding().ivSignUpProfile.setOnClickListener(__ -> {
            Intent intent = new Intent(Intent.ACTION_PICK);
            intent.setType("image/*");
            startActivityForResult(intent, GALLERY);
        });

        getBinding().btSignUp.setOnClickListener(v -> {
            mViewModel.signUpWithEmail(v);
            KeyboardUtil.keyBoardClose(getActivity());
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {
            if (requestCode == GALLERY) {
                String fileName = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
                Uri destinationUri = Uri.fromFile(new File(getCacheDir(), fileName + "png"));
                UCrop.of(data.getData(), destinationUri)
                        .withAspectRatio(UCROP_SQUARE, UCROP_SQUARE)
                        .withMaxResultSize(UCROP_WIDTH_MAX, UCROP_HEIGHT_MAX)
                        .start(getActivity(), this);

            } else if (requestCode == UCrop.REQUEST_CROP) {
                getViewModel().mProfileUri.setValue(UCrop.getOutput(data));
            }
        }

    }
}
