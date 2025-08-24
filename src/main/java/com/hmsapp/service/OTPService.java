package com.hmsapp.service;

import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

@Service
public class OTPService {

    // Store OTP with expiry (here, 5 mins)
    private Map<String, OtpInfo> otpMap = new HashMap<>();

    private static final int EXPIRY_MINUTES = 5;

    public String generateOTP(String mobileNumber) {
        String otp = String.format("%06d", new Random().nextInt(999999));
        OtpInfo otpInfo = new OtpInfo(otp, LocalDateTime.now().plusMinutes(EXPIRY_MINUTES));
        otpMap.put(mobileNumber, otpInfo);
        return otp;
    }

    public boolean validateOtp(String mobileNumber, String userOtp) {
        if (!otpMap.containsKey(mobileNumber)) return false;

        OtpInfo otpInfo = otpMap.get(mobileNumber);

        if (otpInfo.getExpiry().isBefore(LocalDateTime.now())) {
            otpMap.remove(mobileNumber);
            return false; // Expired
        }

        boolean isValid = otpInfo.getOtp().equals(userOtp);

        // Remove OTP after successful validation
        if (isValid) otpMap.remove(mobileNumber);

        return isValid;
    }

    private static class OtpInfo {
        private final String otp;
        private final LocalDateTime expiry;

        public OtpInfo(String otp, LocalDateTime expiry) {
            this.otp = otp;
            this.expiry = expiry;
        }

        public String getOtp() {
            return otp;
        }

        public LocalDateTime getExpiry() {
            return expiry;
        }
    }
}
