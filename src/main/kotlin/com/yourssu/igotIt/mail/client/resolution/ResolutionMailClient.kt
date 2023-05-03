package com.yourssu.igotIt.mail.client.resolution

import com.yourssu.igotIt.common.utils.TimeUtil
import com.yourssu.igotIt.mail.domain.MailClient
import com.yourssu.igotIt.mail.domain.MailRequest
import com.yourssu.igotIt.resolution.domain.Resolution
import com.yourssu.igotIt.resolution.domain.ResolutionRepository
import com.yourssu.igotIt.resolution.domain.vo.Status
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class ResolutionMailClient(
    private val resolutionRepository: ResolutionRepository
) : MailClient {

    // 여기서 email, resolution, user를 dto 리스트로 만들어 mailHourJob -> mailService에 넘겨주자
    @Transactional
    override fun generateRequest(): List<MailRequest> {
        return resolutionRepository.findAll()
            .filter { isDone(it) && isPresentEmail(it) }
            .map { it.updateStatus().run { generateDto(it) } }
            .toList()
    }

    private fun isDone(resolution: Resolution): Boolean {
        val isInProgress = resolution.status.isInProgress()
        val dday = with(resolution) {
            TimeUtil.calculateDday(createdAt!!, period)
        }

        return isInProgress && (dday < 0)
    }

    private fun isPresentEmail(resolution: Resolution): Boolean {
        return !resolution.email.isNullOrEmpty()
    }

    private fun generateDto(resolution: Resolution): ResolutionDoneMailRequest {
        return with(resolution) {
            ResolutionDoneMailRequest(
                email = email!!,
                resolution = this,
                user = this.user
            )
        }
    }
}