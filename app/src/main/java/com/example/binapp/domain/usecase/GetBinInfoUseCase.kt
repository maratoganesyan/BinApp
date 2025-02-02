package com.example.binapp.domain.usecase

import com.example.binapp.domain.model.BinInfo
import com.example.binapp.domain.repository.BinRepositoryInterface

class GetBinInfoUseCase(
    private val repository: BinRepositoryInterface
) {
    suspend operator fun invoke(bin: String): BinInfo {
        return repository.getBinInfo(bin).also { binInfo ->
            repository.saveBinInfo(binInfo, bin)
        }
    }
}