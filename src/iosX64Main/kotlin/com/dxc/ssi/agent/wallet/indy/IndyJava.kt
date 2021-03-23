//package com.dxc.ssi.agent.wallet.indy
//
//import kotlinx.wasm.jsinterop.Object
//import org.apache.commons.lang3.builder.EqualsBuilder
//
///**
// * Common functionality for the APIs, JSON parameters, and results used
// * by the Java wrapper of libindy.
// */
//open class IndyJava {
//    /*
//	 * API
//	 */
//    /**
//     * Common features for API classes.
//     */
//    open class API {
//        /*
//		 * OBJECT METHODS
//		 */
//        @Override
//        override fun hashCode(): Int {
//            return HashCodeBuilder.reflectionHashCode(this, false)
//        }
//
//        @Override
//        override fun equals(other: Object?): Boolean {
//            return EqualsBuilder.reflectionEquals(this, other, false)
//        }
//
//        @Override
//        override fun toString(): String {
//            return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE)
//        }
//
//        companion object {
//            /*
//		 * FUTURES
//		 */
//            private val atomicInteger: AtomicInteger = AtomicInteger()
//            private val futures: Map<Integer, CompletableFuture<*>> = ConcurrentHashMap<Integer, CompletableFuture<*>>()
//
//            /**
//             * Generates and returns a new command handle.
//             *
//             * @return The new command handle.
//             */
//            protected fun newCommandHandle(): Int {
//                return Integer.valueOf(atomicInteger.incrementAndGet())
//            }
//
//            /**
//             * Adds a future to track.
//             *
//             * @param future The future to track.
//             * @return The command handle the future is being tracked against.
//             */
//            protected fun addFuture(future: CompletableFuture<*>?): Int {
//                val commandHandle = newCommandHandle()
//                assert(!futures.containsKey(Integer.valueOf(commandHandle)))
//                futures.put(Integer.valueOf(commandHandle), future)
//                return commandHandle
//            }
//
//            /**
//             * Stops tracking the future associated with the provided command handle and returns it.
//             *
//             * @param xcommand_handle The command handle for the future to stop tracking.
//             * @return The future associated with the command handle.
//             */
//            protected fun removeFuture(xcommand_handle: Int): CompletableFuture<*>? {
//                return futures.remove(Integer.valueOf(xcommand_handle))!!
//            }
//            /*
//		 * ERROR CHECKING
//		 */
//            /**
//             * Sets the provided future with an exception if the error code provided does not indicate success.
//             *
//             * @param future The future.
//             * @param err    The error value to check.
//             * @return true if the error code indicated Success, otherwise false.
//             */
//            protected fun checkResult(future: CompletableFuture<*>, err: Int): Boolean {
//                val errorCode: ErrorCode = ErrorCode.valueOf(err)
//                if (!ErrorCode.Success.equals(errorCode)) {
//                    val indyException: IndyException = IndyException.fromSdkError(err)
//                    future.completeExceptionally(indyException)
//                    return false
//                }
//                return true
//            }
//        }
//    }
//    /*
//	 * JSON PARAMETER
//	 */
//    /**
//     * Base class for parameter objects that return JSON.
//     */
//    abstract class JsonParameter {
//        protected var map: Map<String, Object> = HashMap<String, Object>()
//        /*
//		 * JSON CREATION
//		 */
//        /**
//         * Converts the map of parameters to a JSON string.
//         *
//         * @return The JSON string.
//         */
//        fun toJson(): String {
//            val builder = StringBuilder()
//            builder.append("{")
//            val iterator: Iterator<Map.Entry<String, Object>> = map.entrySet().iterator()
//            while (iterator.hasNext()) {
//                val entry: Map.Entry<String, Object> = iterator.next()
//                val key: String = entry.getKey()
//                val value: Object = entry.getValue()
//                builder.append("\"$key\":")
//                if (value is String) builder.append("\"" + escapeJson(value.toString()) + "\"") else if (value is Boolean) builder.append(
//                    value.toString()
//                ) else if (value is Number) builder.append(value.toString()) else if (value == null) builder.append("null") else throw IllegalArgumentException(
//                    "Invalid value type: " + value + " (" + value.getClass() + ")"
//                )
//                if (iterator.hasNext()) builder.append(",")
//            }
//            builder.append("}")
//            return builder.toString()
//        }
//
//        /*
//		 * OBJECT METHODS
//		 */
//        @Override
//        override fun hashCode(): Int {
//            return map.hashCode()
//        }
//
//        @Override
//        override fun equals(other: Object?): Boolean {
//            return map.equals(other)
//        }
//
//        @Override
//        override fun toString(): String {
//            return toJson()
//        }
//
//        companion object {
//            private fun escapeJson(string: String): String {
//                return string.replace("\\", "\\\\").replace("\"", "\\\"")
//            }
//        }
//    }
//
//    /*
//	 * Result
//	 */
//    abstract class Result {
//        @Override
//        override fun hashCode(): Int {
//            return HashCodeBuilder.reflectionHashCode(this, false)
//        }
//
//        @Override
//        override fun equals(other: Object?): Boolean {
//            return EqualsBuilder.reflectionEquals(this, other, false)
//        }
//
//        @Override
//        override fun toString(): String {
//            return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE)
//        }
//    }
//}