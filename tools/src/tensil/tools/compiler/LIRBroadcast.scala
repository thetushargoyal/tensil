/* SPDX-License-Identifier: Apache-2.0 */
/* Copyright © 2019-2022 Tensil AI Company */

package tensil.tools.compiler

class LIRBroadcast(to: Seq[LIR]) extends LIR {
  def emitWait(tid: Int, tidToWait: Int): Unit =
    to.foreach(_.emitWait(tid, tidToWait))

  def emitMatMul(
      accumulate: Boolean,
      localStride: Int,
      localAddress: MemoryAddress,
      accumulatorStride: Int,
      accumulatorAddress: MemoryAddress,
      size: MemoryAddressRaw,
      tid: Int
  ): Unit =
    to.foreach(
      _.emitMatMul(
        accumulate,
        localStride,
        localAddress,
        accumulatorStride,
        accumulatorAddress,
        size,
        tid
      )
    )

  def emitSIMD(
      accumulate: Boolean,
      simdOp: Int,
      simdSourceLeft: Int,
      simdSourceRight: Int,
      simdDestination: Int,
      writeAccumulatorAddress: MemoryAddress,
      readAccumulatorAddress: MemoryAddress,
      tid: Int
  ): Unit =
    to.foreach(
      _.emitSIMD(
        accumulate,
        simdOp,
        simdSourceLeft,
        simdSourceRight,
        simdDestination,
        writeAccumulatorAddress,
        readAccumulatorAddress,
        tid
      )
    )

  def emitDataMove(
      toLocal: Boolean,
      accumulate: Boolean,
      localStride: Int,
      localAddress: MemoryAddress,
      stride: Int,
      address: MemoryAddress,
      size: MemoryAddressRaw,
      tid: Int
  ): Unit =
    to.foreach(
      _.emitDataMove(
        toLocal,
        accumulate,
        localStride,
        localAddress,
        stride,
        address,
        size,
        tid
      )
    )

  def emitLoadWeights(
      localStride: Int,
      localAddress: MemoryAddress,
      size: MemoryAddressRaw,
      tid: Int
  ): Unit = to.foreach(_.emitLoadWeights(localStride, localAddress, size, tid))

  def endEmit(): Unit = to.foreach(_.endEmit())
}
