#import <Foundation/NSArray.h>
#import <Foundation/NSDictionary.h>
#import <Foundation/NSError.h>
#import <Foundation/NSObject.h>
#import <Foundation/NSSet.h>
#import <Foundation/NSString.h>
#import <Foundation/NSValue.h>

@class Ssi_agentWalletConnector, Ssi_agentKotlinUnit, Ssi_agentConnection, Ssi_agentMessageContext, Ssi_agentTrustPingTrackerService, Ssi_agentCallbacks, Ssi_agentMessageRouterImplRoute, Ssi_agentTrustPingProcessorImpl, Ssi_agentKotlinEnum<E>, Ssi_agentKotlinArray<T>, Ssi_agentMessageEnvelop, Ssi_agentTrustPingRequest, Ssi_agentThread, Ssi_agentTrustPingResponse, Ssi_agentAuthentication, Ssi_agentDidDocument, Ssi_agentConnection_, Ssi_agentConnectionRequest, Ssi_agentConnectionSignature, Ssi_agentConnectionResponse, Ssi_agentPublicKey, Ssi_agentService, Ssi_agentInvitation, Ssi_agentAttribute, Ssi_agentAttach, Ssi_agentCredentialContainer, Ssi_agentCredentialPreview, Ssi_agentCredentialOfferContainer, Ssi_agentCredentialRequestContainer, Ssi_agentCredentialRequestInfo, Ssi_agentEncryptedEnvelop, Ssi_agentForward, Ssi_agentData, Ssi_agentPresentationContainer, Ssi_agentPresentationRequestContainer, Ssi_agentKotlinPair<__covariant A, __covariant B>, Ssi_agentMessage, Ssi_agentCredentialIssuenceState, Ssi_agentCredentialVerificationState, Ssi_agentActionResult, Ssi_agentActionParams, Ssi_agentAbstractMessageProcessor, Ssi_agentTrustPingProcessorImplTrustPingMessageType, Ssi_agentCredVerifierProcessorImplCredVerifyMessageType, Ssi_agentCredIssuerProcessorImplCredIssueMessageType, Ssi_agentDidExchangeProcessorImplDidExchangeMessageType, Ssi_agentCredentialExchangeRecord, Ssi_agentDidConfig, Ssi_agentIdentityDetails, Ssi_agentBasicMessageWithTypeOnly, Ssi_agentReceivedUnpackedMessage, Ssi_agentTransportableMessage, Ssi_agentAppSocketState, Ssi_agentKotlinThrowable, Ssi_agentSocketClosureDetails, NSURLSessionWebSocketTask, Ssi_agentWebSocketWrapper, Ssi_agentCallbackResult, Ssi_agentProblemReport, Ssi_agentRevocationRegistryDefinitionId, Ssi_agentInterval, Ssi_agentRevocationRegistryEntry, Ssi_agentRevocationState, Ssi_agentIndyException, Ssi_agentCallbackDataWrapper, Ssi_agentKotlinAtomicInt, Ssi_agentIntCallbackResult, Ssi_agentSimpleCallbackResult, Ssi_agentStringCallbackResult, Ssi_agentKotlinx_serialization_jsonJson, Ssi_agentErrorCode, Ssi_agentErrorDetails, Ssi_agentKotlinException, Ssi_agentIndyLedgerConnectorConfiguration, Ssi_agentIndyLedgerConnectorConfigurationGenesisMode, Ssi_agentParseResponseResult, Ssi_agentParseRegistryResponseResult, Ssi_agentPool, Ssi_agentLedgerParseGetCredDefResponseCallbackResult, Ssi_agentLedgerParseGetRevocRegDefResponseCallbackResult, Ssi_agentLedgerParseGetRevocRegDeltaResponseCallbackResult, Ssi_agentLedgerParseGetSchemaResponseCallbackResult, Ssi_agentPoolJSONParametersCreatePoolLedgerConfigJSONParameter, Ssi_agentPoolJSONParametersOpenPoolLedgerJSONParameter, Ssi_agentTailsConfig, Ssi_agentTailsRequest, Ssi_agentKotlinByteArray, Ssi_agentTailsResponse, Ssi_agentKotlinRuntimeException, Ssi_agentKotlinIllegalArgumentException, Ssi_agentObjectHolder<T>, Ssi_agentWalletRecordType, Ssi_agentCommonWalletRecord, Ssi_agentRetrievedWalletRecords, Ssi_agentStorageConfig, Ssi_agentWalletConfig, Ssi_agentWalletPassword, Ssi_agentWalletRecordTag, Ssi_agentRestriction, Ssi_agentCredentialAttributeReference, Ssi_agentCredentialPredicateReference, Ssi_agentCredentialReference, Ssi_agentIndyCredInfo, Ssi_agentIndyCredentialForTheRequest, Ssi_agentIndyProof, Ssi_agentRequestedProof, Ssi_agentProofIdentifier, Ssi_agentIndyPresentation, Ssi_agentIndyPresentationRequest, Ssi_agentIndyProofDetails, Ssi_agentIndySchema, Ssi_agentIndySchemaId, Ssi_agentRequestedAttributeInfo, Ssi_agentRequestedPredicateInfo, Ssi_agentRequestedCredentials, Ssi_agentRevealedAttributeReference, Ssi_agentRevealedPredicateReference, Ssi_agentIndyRevocationRegistryDefinition, Ssi_agentIndyCredential, Ssi_agentIndyCredentialPubKeys, Ssi_agentIndyCredentialDefinition, Ssi_agentIndyCredentialDefinitionId, Ssi_agentIndyKeyCorrectnessProof, Ssi_agentIndyCredentialOffer, Ssi_agentIndyCredentialRequest, Ssi_agentIndyCredentialRequestMetadata, Ssi_agentMasterSecretBlindingData, Ssi_agentWallet, Ssi_agentProverCreateCredentialRequestResult, Ssi_agentAnoncredsProverCreateCredentialReqCallbackResult, Ssi_agentCryptoPackedMessageCallbackResult, Ssi_agentCryptoUnPackedMessageCallbackResult, Ssi_agentCreateAndStoreMyDidResult, Ssi_agentDidCreateAndStoreMyDidCallbackResult, Ssi_agentCallbackHandler, NSData, Ssi_agentKotlinIllegalStateException, Ssi_agentKotlinx_coroutines_coreCancellationException, Ssi_agentKotlinx_serialization_coreSerializersModule, Ssi_agentKotlinx_serialization_coreSerialKind, Ssi_agentKotlinNothing, Ssi_agentKotlinx_serialization_jsonJsonElement, Ssi_agentKotlinByteIterator, Ssi_agentKotlinx_coroutines_coreAtomicDesc, Ssi_agentKotlinx_coroutines_coreLockFreeLinkedListNodePrepareOp, Ssi_agentKotlinx_coroutines_coreAtomicOp<__contravariant T>, Ssi_agentKotlinx_coroutines_coreOpDescriptor, Ssi_agentKotlinx_coroutines_coreLockFreeLinkedListNode, Ssi_agentKotlinx_coroutines_coreLockFreeLinkedListNodeAbstractAtomicDesc, Ssi_agentKotlinx_coroutines_coreLockFreeLinkedListNodeAddLastDesc<T>, Ssi_agentKotlinx_coroutines_coreLockFreeLinkedListNodeRemoveFirstDesc<T>;

@protocol Ssi_agentConnectionInitiatorController, Ssi_agentDidExchangeProcessor, Ssi_agentTrustPingProcessor, Ssi_agentMessageRouter, Ssi_agentLedgerConnector, Ssi_agentTransport, Ssi_agentKotlinComparable, Ssi_agentMessageListener, Ssi_agentKotlinx_serialization_coreKSerializer, Ssi_agentCredentialRequest, Ssi_agentCredentialRequestMetadata, Ssi_agentState, Ssi_agentAction, Ssi_agentStateMachine, Ssi_agentDidExchangeState, Ssi_agentCredentialVerificationAction, Ssi_agentDidExchangeAction, Ssi_agentCredentialIssuenceAction, Ssi_agentMessageProcessor, Ssi_agentMessageType, Ssi_agentCredVerifierProcessor, Ssi_agentCredIssuerProcessor, Ssi_agentCredentialDefinition, Ssi_agentKotlinx_coroutines_coreChannel, Ssi_agentConnectionResponderController, Ssi_agentCredReceiverController, Ssi_agentCredIssuerController, Ssi_agentCredPresenterController, Ssi_agentCredVerifierController, Ssi_agentSsiAgentApi, Ssi_agentSsiAgentBuilder, Ssi_agentEnvironment, Ssi_agentIssuer, Ssi_agentProver, Ssi_agentTrustee, Ssi_agentVerifier, Ssi_agentWalletHolder, Ssi_agentCredentialDefinitionId, Ssi_agentRevocationRegistryDefinition, Ssi_agentSchemaId, Ssi_agentSchema, Ssi_agentCredential, Ssi_agentCredentialOffer, Ssi_agentPresentationRequest, Ssi_agentPresentation, Ssi_agentCallbackData, Ssi_agentKotlinx_serialization_coreEncoder, Ssi_agentKotlinx_serialization_coreSerialDescriptor, Ssi_agentKotlinx_serialization_coreSerializationStrategy, Ssi_agentKotlinx_serialization_coreDecoder, Ssi_agentKotlinx_serialization_coreDeserializationStrategy, Ssi_agentKotlinCoroutineContext, Ssi_agentKotlinx_coroutines_coreDeferred, Ssi_agentFromString, Ssi_agentContainsCredentialDefinitionId, Ssi_agentKotlinIterator, Ssi_agentKotlinx_coroutines_coreSelectClause2, Ssi_agentKotlinx_coroutines_coreSendChannel, Ssi_agentKotlinx_coroutines_coreChannelIterator, Ssi_agentKotlinx_coroutines_coreSelectClause1, Ssi_agentKotlinx_coroutines_coreReceiveChannel, Ssi_agentKotlinx_serialization_coreCompositeEncoder, Ssi_agentKotlinAnnotation, Ssi_agentKotlinx_serialization_coreCompositeDecoder, Ssi_agentKotlinCoroutineContextElement, Ssi_agentKotlinCoroutineContextKey, Ssi_agentKotlinx_coroutines_coreChildHandle, Ssi_agentKotlinx_coroutines_coreChildJob, Ssi_agentKotlinx_coroutines_coreDisposableHandle, Ssi_agentKotlinx_coroutines_coreJob, Ssi_agentKotlinSequence, Ssi_agentKotlinx_coroutines_coreSelectClause0, Ssi_agentKotlinx_serialization_coreSerialFormat, Ssi_agentKotlinx_serialization_coreStringFormat, Ssi_agentKotlinx_coroutines_coreSelectInstance, Ssi_agentKotlinSuspendFunction1, Ssi_agentKotlinx_serialization_coreSerializersModuleCollector, Ssi_agentKotlinKClass, Ssi_agentKotlinx_coroutines_coreParentJob, Ssi_agentKotlinSuspendFunction0, Ssi_agentKotlinContinuation, Ssi_agentKotlinFunction, Ssi_agentKotlinKDeclarationContainer, Ssi_agentKotlinKAnnotatedElement, Ssi_agentKotlinKClassifier;

NS_ASSUME_NONNULL_BEGIN
#pragma clang diagnostic push
#pragma clang diagnostic ignored "-Wunknown-warning-option"
#pragma clang diagnostic ignored "-Wincompatible-property-type"
#pragma clang diagnostic ignored "-Wnullability"

__attribute__((swift_name("KotlinBase")))
@interface Ssi_agentBase : NSObject
- (instancetype)init __attribute__((unavailable));
+ (instancetype)new __attribute__((unavailable));
+ (void)initialize __attribute__((objc_requires_super));
@end;

@interface Ssi_agentBase (Ssi_agentBaseCopying) <NSCopying>
@end;

__attribute__((swift_name("KotlinMutableSet")))
@interface Ssi_agentMutableSet<ObjectType> : NSMutableSet<ObjectType>
@end;

__attribute__((swift_name("KotlinMutableDictionary")))
@interface Ssi_agentMutableDictionary<KeyType, ObjectType> : NSMutableDictionary<KeyType, ObjectType>
@end;

@interface NSError (NSErrorSsi_agentKotlinException)
@property (readonly) id _Nullable kotlinException;
@end;

__attribute__((swift_name("KotlinNumber")))
@interface Ssi_agentNumber : NSNumber
- (instancetype)initWithChar:(char)value __attribute__((unavailable));
- (instancetype)initWithUnsignedChar:(unsigned char)value __attribute__((unavailable));
- (instancetype)initWithShort:(short)value __attribute__((unavailable));
- (instancetype)initWithUnsignedShort:(unsigned short)value __attribute__((unavailable));
- (instancetype)initWithInt:(int)value __attribute__((unavailable));
- (instancetype)initWithUnsignedInt:(unsigned int)value __attribute__((unavailable));
- (instancetype)initWithLong:(long)value __attribute__((unavailable));
- (instancetype)initWithUnsignedLong:(unsigned long)value __attribute__((unavailable));
- (instancetype)initWithLongLong:(long long)value __attribute__((unavailable));
- (instancetype)initWithUnsignedLongLong:(unsigned long long)value __attribute__((unavailable));
- (instancetype)initWithFloat:(float)value __attribute__((unavailable));
- (instancetype)initWithDouble:(double)value __attribute__((unavailable));
- (instancetype)initWithBool:(BOOL)value __attribute__((unavailable));
- (instancetype)initWithInteger:(NSInteger)value __attribute__((unavailable));
- (instancetype)initWithUnsignedInteger:(NSUInteger)value __attribute__((unavailable));
+ (instancetype)numberWithChar:(char)value __attribute__((unavailable));
+ (instancetype)numberWithUnsignedChar:(unsigned char)value __attribute__((unavailable));
+ (instancetype)numberWithShort:(short)value __attribute__((unavailable));
+ (instancetype)numberWithUnsignedShort:(unsigned short)value __attribute__((unavailable));
+ (instancetype)numberWithInt:(int)value __attribute__((unavailable));
+ (instancetype)numberWithUnsignedInt:(unsigned int)value __attribute__((unavailable));
+ (instancetype)numberWithLong:(long)value __attribute__((unavailable));
+ (instancetype)numberWithUnsignedLong:(unsigned long)value __attribute__((unavailable));
+ (instancetype)numberWithLongLong:(long long)value __attribute__((unavailable));
+ (instancetype)numberWithUnsignedLongLong:(unsigned long long)value __attribute__((unavailable));
+ (instancetype)numberWithFloat:(float)value __attribute__((unavailable));
+ (instancetype)numberWithDouble:(double)value __attribute__((unavailable));
+ (instancetype)numberWithBool:(BOOL)value __attribute__((unavailable));
+ (instancetype)numberWithInteger:(NSInteger)value __attribute__((unavailable));
+ (instancetype)numberWithUnsignedInteger:(NSUInteger)value __attribute__((unavailable));
@end;

__attribute__((swift_name("KotlinByte")))
@interface Ssi_agentByte : Ssi_agentNumber
- (instancetype)initWithChar:(char)value;
+ (instancetype)numberWithChar:(char)value;
@end;

__attribute__((swift_name("KotlinUByte")))
@interface Ssi_agentUByte : Ssi_agentNumber
- (instancetype)initWithUnsignedChar:(unsigned char)value;
+ (instancetype)numberWithUnsignedChar:(unsigned char)value;
@end;

__attribute__((swift_name("KotlinShort")))
@interface Ssi_agentShort : Ssi_agentNumber
- (instancetype)initWithShort:(short)value;
+ (instancetype)numberWithShort:(short)value;
@end;

__attribute__((swift_name("KotlinUShort")))
@interface Ssi_agentUShort : Ssi_agentNumber
- (instancetype)initWithUnsignedShort:(unsigned short)value;
+ (instancetype)numberWithUnsignedShort:(unsigned short)value;
@end;

__attribute__((swift_name("KotlinInt")))
@interface Ssi_agentInt : Ssi_agentNumber
- (instancetype)initWithInt:(int)value;
+ (instancetype)numberWithInt:(int)value;
@end;

__attribute__((swift_name("KotlinUInt")))
@interface Ssi_agentUInt : Ssi_agentNumber
- (instancetype)initWithUnsignedInt:(unsigned int)value;
+ (instancetype)numberWithUnsignedInt:(unsigned int)value;
@end;

__attribute__((swift_name("KotlinLong")))
@interface Ssi_agentLong : Ssi_agentNumber
- (instancetype)initWithLongLong:(long long)value;
+ (instancetype)numberWithLongLong:(long long)value;
@end;

__attribute__((swift_name("KotlinULong")))
@interface Ssi_agentULong : Ssi_agentNumber
- (instancetype)initWithUnsignedLongLong:(unsigned long long)value;
+ (instancetype)numberWithUnsignedLongLong:(unsigned long long)value;
@end;

__attribute__((swift_name("KotlinFloat")))
@interface Ssi_agentFloat : Ssi_agentNumber
- (instancetype)initWithFloat:(float)value;
+ (instancetype)numberWithFloat:(float)value;
@end;

__attribute__((swift_name("KotlinDouble")))
@interface Ssi_agentDouble : Ssi_agentNumber
- (instancetype)initWithDouble:(double)value;
+ (instancetype)numberWithDouble:(double)value;
@end;

__attribute__((swift_name("KotlinBoolean")))
@interface Ssi_agentBoolean : Ssi_agentNumber
- (instancetype)initWithBool:(BOOL)value;
+ (instancetype)numberWithBool:(BOOL)value;
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("TrustPingTrackerService")))
@interface Ssi_agentTrustPingTrackerService : Ssi_agentBase
- (instancetype)initWithWalletConnector:(Ssi_agentWalletConnector *)walletConnector connectionInitiatorController:(id<Ssi_agentConnectionInitiatorController>)connectionInitiatorController __attribute__((swift_name("init(walletConnector:connectionInitiatorController:)"))) __attribute__((objc_designated_initializer));
- (void)shutdown __attribute__((swift_name("shutdown()")));

/**
 @note This method converts instances of CancellationException to errors.
 Other uncaught Kotlin exceptions are fatal.
*/
- (void)trackWithCompletionHandler:(void (^)(Ssi_agentKotlinUnit * _Nullable, NSError * _Nullable))completionHandler __attribute__((swift_name("track(completionHandler:)")));
- (void)trustPingResponseReceivedEventConnection:(Ssi_agentConnection *)connection __attribute__((swift_name("trustPingResponseReceivedEvent(connection:)")));
- (void)trustPingSentOverConnectionEventConnection:(Ssi_agentConnection *)connection __attribute__((swift_name("trustPingSentOverConnectionEvent(connection:)")));
@property (readonly) id<Ssi_agentConnectionInitiatorController> connectionInitiatorController __attribute__((swift_name("connectionInitiatorController")));
@property (readonly) Ssi_agentWalletConnector *walletConnector __attribute__((swift_name("walletConnector")));
@end;

__attribute__((swift_name("MessageRouter")))
@protocol Ssi_agentMessageRouter
@required

/**
 @note This method converts instances of CancellationException to errors.
 Other uncaught Kotlin exceptions are fatal.
*/
- (void)routeAndProcessMessageMessageContext:(Ssi_agentMessageContext *)messageContext completionHandler:(void (^)(Ssi_agentKotlinUnit * _Nullable, NSError * _Nullable))completionHandler __attribute__((swift_name("routeAndProcessMessage(messageContext:completionHandler:)")));
@property (readonly) id<Ssi_agentDidExchangeProcessor> didExchangeProcessor __attribute__((swift_name("didExchangeProcessor")));
@property (readonly) id<Ssi_agentTrustPingProcessor> trustPingProcessor __attribute__((swift_name("trustPingProcessor")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("MessageRouterImpl")))
@interface Ssi_agentMessageRouterImpl : Ssi_agentBase <Ssi_agentMessageRouter>
- (instancetype)initWithWalletConnector:(Ssi_agentWalletConnector *)walletConnector ledgerConnector:(id<Ssi_agentLedgerConnector>)ledgerConnector trustPingTrackerService:(Ssi_agentTrustPingTrackerService *)trustPingTrackerService transport:(id<Ssi_agentTransport>)transport callbacks:(Ssi_agentCallbacks *)callbacks __attribute__((swift_name("init(walletConnector:ledgerConnector:trustPingTrackerService:transport:callbacks:)"))) __attribute__((objc_designated_initializer));
- (Ssi_agentMessageRouterImplRoute *)determineRouteMessageContext:(Ssi_agentMessageContext *)messageContext __attribute__((swift_name("determineRoute(messageContext:)")));

/**
 @note This method converts instances of CancellationException to errors.
 Other uncaught Kotlin exceptions are fatal.
*/
- (void)routeAndProcessMessageMessageContext:(Ssi_agentMessageContext *)messageContext completionHandler:(void (^)(Ssi_agentKotlinUnit * _Nullable, NSError * _Nullable))completionHandler __attribute__((swift_name("routeAndProcessMessage(messageContext:completionHandler:)")));
@property (readonly) id<Ssi_agentDidExchangeProcessor> didExchangeProcessor __attribute__((swift_name("didExchangeProcessor")));
@property (readonly) Ssi_agentTrustPingProcessorImpl *trustPingProcessor __attribute__((swift_name("trustPingProcessor")));
@end;

__attribute__((swift_name("KotlinComparable")))
@protocol Ssi_agentKotlinComparable
@required
- (int32_t)compareToOther:(id _Nullable)other __attribute__((swift_name("compareTo(other:)")));
@end;

__attribute__((swift_name("KotlinEnum")))
@interface Ssi_agentKotlinEnum<E> : Ssi_agentBase <Ssi_agentKotlinComparable>
- (instancetype)initWithName:(NSString *)name ordinal:(int32_t)ordinal __attribute__((swift_name("init(name:ordinal:)"))) __attribute__((objc_designated_initializer));
- (int32_t)compareToOther:(E)other __attribute__((swift_name("compareTo(other:)")));
- (BOOL)isEqual:(id _Nullable)other __attribute__((swift_name("isEqual(_:)")));
- (NSUInteger)hash __attribute__((swift_name("hash()")));
- (NSString *)description __attribute__((swift_name("description()")));
@property (readonly) NSString *name __attribute__((swift_name("name")));
@property (readonly) int32_t ordinal __attribute__((swift_name("ordinal")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("MessageRouterImpl.Route")))
@interface Ssi_agentMessageRouterImplRoute : Ssi_agentKotlinEnum<Ssi_agentMessageRouterImplRoute *>
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
- (instancetype)initWithName:(NSString *)name ordinal:(int32_t)ordinal __attribute__((swift_name("init(name:ordinal:)"))) __attribute__((objc_designated_initializer)) __attribute__((unavailable));
@property (class, readonly) Ssi_agentMessageRouterImplRoute *didexchange __attribute__((swift_name("didexchange")));
@property (class, readonly) Ssi_agentMessageRouterImplRoute *credissuer __attribute__((swift_name("credissuer")));
@property (class, readonly) Ssi_agentMessageRouterImplRoute *credverifier __attribute__((swift_name("credverifier")));
@property (class, readonly) Ssi_agentMessageRouterImplRoute *trustping __attribute__((swift_name("trustping")));
+ (Ssi_agentKotlinArray<Ssi_agentMessageRouterImplRoute *> *)values __attribute__((swift_name("values()")));
@end;

__attribute__((swift_name("MessageListener")))
@protocol Ssi_agentMessageListener
@required

/**
 @note This method converts instances of CancellationException to errors.
 Other uncaught Kotlin exceptions are fatal.
*/
- (void)listenWithCompletionHandler:(void (^)(Ssi_agentKotlinUnit * _Nullable, NSError * _Nullable))completionHandler __attribute__((swift_name("listen(completionHandler:)")));
- (void)shutdown __attribute__((swift_name("shutdown()")));
@property (readonly) id<Ssi_agentMessageRouter> messageRouter __attribute__((swift_name("messageRouter")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("MessageListenerImpl")))
@interface Ssi_agentMessageListenerImpl : Ssi_agentBase <Ssi_agentMessageListener>
- (instancetype)initWithTransport:(id<Ssi_agentTransport>)transport walletConnector:(Ssi_agentWalletConnector *)walletConnector ledgerConnector:(id<Ssi_agentLedgerConnector>)ledgerConnector trustPingTrackerService:(Ssi_agentTrustPingTrackerService *)trustPingTrackerService callbacks:(Ssi_agentCallbacks *)callbacks __attribute__((swift_name("init(transport:walletConnector:ledgerConnector:trustPingTrackerService:callbacks:)"))) __attribute__((objc_designated_initializer));

/**
 @note This method converts instances of CancellationException to errors.
 Other uncaught Kotlin exceptions are fatal.
*/
- (void)listenWithCompletionHandler:(void (^)(Ssi_agentKotlinUnit * _Nullable, NSError * _Nullable))completionHandler __attribute__((swift_name("listen(completionHandler:)")));
- (void)shutdown __attribute__((swift_name("shutdown()")));

/**
 @note This method converts instances of CancellationException to errors.
 Other uncaught Kotlin exceptions are fatal.
*/
- (void)unpackAndBuildMesageContextReceivedMessage:(Ssi_agentMessageEnvelop *)receivedMessage completionHandler:(void (^)(Ssi_agentMessageContext * _Nullable, NSError * _Nullable))completionHandler __attribute__((swift_name("unpackAndBuildMesageContext(receivedMessage:completionHandler:)")));
@property (readonly) id<Ssi_agentMessageRouter> messageRouter __attribute__((swift_name("messageRouter")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("TrustPingRequest")))
@interface Ssi_agentTrustPingRequest : Ssi_agentBase
- (instancetype)initWithType:(NSString *)type id:(NSString *)id responseRequested:(BOOL)responseRequested __attribute__((swift_name("init(type:id:responseRequested:)"))) __attribute__((objc_designated_initializer));
- (NSString *)component1 __attribute__((swift_name("component1()")));
- (NSString *)component2 __attribute__((swift_name("component2()")));
- (BOOL)component3 __attribute__((swift_name("component3()")));
- (Ssi_agentTrustPingRequest *)doCopyType:(NSString *)type id:(NSString *)id responseRequested:(BOOL)responseRequested __attribute__((swift_name("doCopy(type:id:responseRequested:)")));
- (BOOL)isEqual:(id _Nullable)other __attribute__((swift_name("isEqual(_:)")));
- (NSUInteger)hash __attribute__((swift_name("hash()")));
- (NSString *)description __attribute__((swift_name("description()")));
@property (readonly) NSString *id __attribute__((swift_name("id")));
@property (readonly) BOOL responseRequested __attribute__((swift_name("responseRequested")));
@property (readonly) NSString *type __attribute__((swift_name("type")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("TrustPingRequest.Companion")))
@interface Ssi_agentTrustPingRequestCompanion : Ssi_agentBase
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
+ (instancetype)companion __attribute__((swift_name("init()")));
- (id<Ssi_agentKotlinx_serialization_coreKSerializer>)serializer __attribute__((swift_name("serializer()")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("TrustPingResponse")))
@interface Ssi_agentTrustPingResponse : Ssi_agentBase
- (instancetype)initWithType:(NSString *)type id:(NSString *)id thread:(Ssi_agentThread *)thread __attribute__((swift_name("init(type:id:thread:)"))) __attribute__((objc_designated_initializer));
- (NSString *)component1 __attribute__((swift_name("component1()")));
- (NSString *)component2 __attribute__((swift_name("component2()")));
- (Ssi_agentThread *)component3 __attribute__((swift_name("component3()")));
- (Ssi_agentTrustPingResponse *)doCopyType:(NSString *)type id:(NSString *)id thread:(Ssi_agentThread *)thread __attribute__((swift_name("doCopy(type:id:thread:)")));
- (BOOL)isEqual:(id _Nullable)other __attribute__((swift_name("isEqual(_:)")));
- (NSUInteger)hash __attribute__((swift_name("hash()")));
- (NSString *)description __attribute__((swift_name("description()")));
@property (readonly) NSString *id __attribute__((swift_name("id")));
@property (readonly) Ssi_agentThread *thread __attribute__((swift_name("thread")));
@property (readonly) NSString *type __attribute__((swift_name("type")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("TrustPingResponse.Companion")))
@interface Ssi_agentTrustPingResponseCompanion : Ssi_agentBase
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
+ (instancetype)companion __attribute__((swift_name("init()")));
- (id<Ssi_agentKotlinx_serialization_coreKSerializer>)serializer __attribute__((swift_name("serializer()")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("Authentication")))
@interface Ssi_agentAuthentication : Ssi_agentBase
- (instancetype)initWithType:(NSString *)type publicKey:(NSString *)publicKey __attribute__((swift_name("init(type:publicKey:)"))) __attribute__((objc_designated_initializer));
- (NSString *)component1 __attribute__((swift_name("component1()")));
- (NSString *)component2 __attribute__((swift_name("component2()")));
- (Ssi_agentAuthentication *)doCopyType:(NSString *)type publicKey:(NSString *)publicKey __attribute__((swift_name("doCopy(type:publicKey:)")));
- (BOOL)isEqual:(id _Nullable)other __attribute__((swift_name("isEqual(_:)")));
- (NSUInteger)hash __attribute__((swift_name("hash()")));
- (NSString *)description __attribute__((swift_name("description()")));
@property (readonly) NSString *publicKey __attribute__((swift_name("publicKey")));
@property (readonly) NSString *type __attribute__((swift_name("type")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("Authentication.Companion")))
@interface Ssi_agentAuthenticationCompanion : Ssi_agentBase
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
+ (instancetype)companion __attribute__((swift_name("init()")));
- (id<Ssi_agentKotlinx_serialization_coreKSerializer>)serializer __attribute__((swift_name("serializer()")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("Connection_")))
@interface Ssi_agentConnection_ : Ssi_agentBase
- (instancetype)initWithDid:(NSString *)did didDocument:(Ssi_agentDidDocument *)didDocument __attribute__((swift_name("init(did:didDocument:)"))) __attribute__((objc_designated_initializer));
- (NSString *)component1 __attribute__((swift_name("component1()")));
- (Ssi_agentDidDocument *)component2 __attribute__((swift_name("component2()")));
- (Ssi_agentConnection_ *)doCopyDid:(NSString *)did didDocument:(Ssi_agentDidDocument *)didDocument __attribute__((swift_name("doCopy(did:didDocument:)")));
- (BOOL)isEqual:(id _Nullable)other __attribute__((swift_name("isEqual(_:)")));
- (NSUInteger)hash __attribute__((swift_name("hash()")));
- (NSString *)description __attribute__((swift_name("description()")));
@property (readonly) NSString *did __attribute__((swift_name("did")));
@property (readonly) Ssi_agentDidDocument *didDocument __attribute__((swift_name("didDocument")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("Connection_.Companion")))
@interface Ssi_agentConnection_Companion : Ssi_agentBase
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
+ (instancetype)companion __attribute__((swift_name("init()")));
- (id<Ssi_agentKotlinx_serialization_coreKSerializer>)serializer __attribute__((swift_name("serializer()")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("ConnectionRequest")))
@interface Ssi_agentConnectionRequest : Ssi_agentBase
- (instancetype)initWithType:(NSString *)type id:(NSString *)id label:(NSString *)label imageUrl:(NSString * _Nullable)imageUrl connection:(Ssi_agentConnection_ *)connection __attribute__((swift_name("init(type:id:label:imageUrl:connection:)"))) __attribute__((objc_designated_initializer));
- (NSString *)component1 __attribute__((swift_name("component1()")));
- (NSString *)component2 __attribute__((swift_name("component2()")));
- (NSString *)component3 __attribute__((swift_name("component3()")));
- (NSString * _Nullable)component4 __attribute__((swift_name("component4()")));
- (Ssi_agentConnection_ *)component5 __attribute__((swift_name("component5()")));
- (Ssi_agentConnectionRequest *)doCopyType:(NSString *)type id:(NSString *)id label:(NSString *)label imageUrl:(NSString * _Nullable)imageUrl connection:(Ssi_agentConnection_ *)connection __attribute__((swift_name("doCopy(type:id:label:imageUrl:connection:)")));
- (BOOL)isEqual:(id _Nullable)other __attribute__((swift_name("isEqual(_:)")));
- (NSUInteger)hash __attribute__((swift_name("hash()")));
- (NSString *)description __attribute__((swift_name("description()")));
@property (readonly) Ssi_agentConnection_ *connection __attribute__((swift_name("connection")));
@property (readonly) NSString *id __attribute__((swift_name("id")));
@property (readonly) NSString * _Nullable imageUrl __attribute__((swift_name("imageUrl")));
@property (readonly) NSString *label __attribute__((swift_name("label")));
@property (readonly) NSString *type __attribute__((swift_name("type")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("ConnectionRequest.Companion")))
@interface Ssi_agentConnectionRequestCompanion : Ssi_agentBase
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
+ (instancetype)companion __attribute__((swift_name("init()")));
- (id<Ssi_agentKotlinx_serialization_coreKSerializer>)serializer __attribute__((swift_name("serializer()")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("ConnectionResponse")))
@interface Ssi_agentConnectionResponse : Ssi_agentBase
- (instancetype)initWithType:(NSString *)type id:(NSString *)id thread:(Ssi_agentThread *)thread connectionSignature:(Ssi_agentConnectionSignature *)connectionSignature __attribute__((swift_name("init(type:id:thread:connectionSignature:)"))) __attribute__((objc_designated_initializer));
- (NSString *)component1 __attribute__((swift_name("component1()")));
- (NSString *)component2 __attribute__((swift_name("component2()")));
- (Ssi_agentThread *)component3 __attribute__((swift_name("component3()")));
- (Ssi_agentConnectionSignature *)component4 __attribute__((swift_name("component4()")));
- (Ssi_agentConnectionResponse *)doCopyType:(NSString *)type id:(NSString *)id thread:(Ssi_agentThread *)thread connectionSignature:(Ssi_agentConnectionSignature *)connectionSignature __attribute__((swift_name("doCopy(type:id:thread:connectionSignature:)")));
- (BOOL)isEqual:(id _Nullable)other __attribute__((swift_name("isEqual(_:)")));
- (NSUInteger)hash __attribute__((swift_name("hash()")));
- (NSString *)description __attribute__((swift_name("description()")));
@property (readonly) Ssi_agentConnectionSignature *connectionSignature __attribute__((swift_name("connectionSignature")));
@property (readonly) NSString *id __attribute__((swift_name("id")));
@property (readonly) Ssi_agentThread *thread __attribute__((swift_name("thread")));
@property (readonly) NSString *type __attribute__((swift_name("type")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("ConnectionResponse.Companion")))
@interface Ssi_agentConnectionResponseCompanion : Ssi_agentBase
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
+ (instancetype)companion __attribute__((swift_name("init()")));
- (id<Ssi_agentKotlinx_serialization_coreKSerializer>)serializer __attribute__((swift_name("serializer()")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("ConnectionSignature")))
@interface Ssi_agentConnectionSignature : Ssi_agentBase
- (instancetype)initWithType:(NSString *)type signature:(NSString *)signature signedData:(NSString *)signedData signer:(NSString *)signer __attribute__((swift_name("init(type:signature:signedData:signer:)"))) __attribute__((objc_designated_initializer));
- (NSString *)component1 __attribute__((swift_name("component1()")));
- (NSString *)component2 __attribute__((swift_name("component2()")));
- (NSString *)component3 __attribute__((swift_name("component3()")));
- (NSString *)component4 __attribute__((swift_name("component4()")));
- (Ssi_agentConnectionSignature *)doCopyType:(NSString *)type signature:(NSString *)signature signedData:(NSString *)signedData signer:(NSString *)signer __attribute__((swift_name("doCopy(type:signature:signedData:signer:)")));
- (BOOL)isEqual:(id _Nullable)other __attribute__((swift_name("isEqual(_:)")));
- (NSUInteger)hash __attribute__((swift_name("hash()")));
- (NSString *)description __attribute__((swift_name("description()")));
@property (readonly) NSString *signature __attribute__((swift_name("signature")));
@property (readonly) NSString *signedData __attribute__((swift_name("signedData")));
@property (readonly) NSString *signer __attribute__((swift_name("signer")));
@property (readonly) NSString *type __attribute__((swift_name("type")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("ConnectionSignature.Companion")))
@interface Ssi_agentConnectionSignatureCompanion : Ssi_agentBase
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
+ (instancetype)companion __attribute__((swift_name("init()")));
- (id<Ssi_agentKotlinx_serialization_coreKSerializer>)serializer __attribute__((swift_name("serializer()")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("DidDocument")))
@interface Ssi_agentDidDocument : Ssi_agentBase
- (instancetype)initWithContext:(NSString *)context id:(NSString *)id publicKey:(NSArray<Ssi_agentPublicKey *> *)publicKey authentication:(NSArray<Ssi_agentAuthentication *> * _Nullable)authentication service:(NSArray<Ssi_agentService *> * _Nullable)service __attribute__((swift_name("init(context:id:publicKey:authentication:service:)"))) __attribute__((objc_designated_initializer));
- (NSString *)component1 __attribute__((swift_name("component1()")));
- (NSString *)component2 __attribute__((swift_name("component2()")));
- (NSArray<Ssi_agentPublicKey *> *)component3 __attribute__((swift_name("component3()")));
- (NSArray<Ssi_agentAuthentication *> * _Nullable)component4 __attribute__((swift_name("component4()")));
- (NSArray<Ssi_agentService *> * _Nullable)component5 __attribute__((swift_name("component5()")));
- (Ssi_agentDidDocument *)doCopyContext:(NSString *)context id:(NSString *)id publicKey:(NSArray<Ssi_agentPublicKey *> *)publicKey authentication:(NSArray<Ssi_agentAuthentication *> * _Nullable)authentication service:(NSArray<Ssi_agentService *> * _Nullable)service __attribute__((swift_name("doCopy(context:id:publicKey:authentication:service:)")));
- (BOOL)isEqual:(id _Nullable)other __attribute__((swift_name("isEqual(_:)")));
- (NSUInteger)hash __attribute__((swift_name("hash()")));
- (NSString *)description __attribute__((swift_name("description()")));
@property (readonly) NSArray<Ssi_agentAuthentication *> * _Nullable authentication __attribute__((swift_name("authentication")));
@property (readonly) NSString *context __attribute__((swift_name("context")));
@property (readonly) NSString *id __attribute__((swift_name("id")));
@property (readonly) NSArray<Ssi_agentPublicKey *> *publicKey __attribute__((swift_name("publicKey")));
@property (readonly) NSArray<Ssi_agentService *> * _Nullable service __attribute__((swift_name("service")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("DidDocument.Companion")))
@interface Ssi_agentDidDocumentCompanion : Ssi_agentBase
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
+ (instancetype)companion __attribute__((swift_name("init()")));
- (id<Ssi_agentKotlinx_serialization_coreKSerializer>)serializer __attribute__((swift_name("serializer()")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("Invitation")))
@interface Ssi_agentInvitation : Ssi_agentBase
- (instancetype)initWithType:(NSString *)type id:(NSString *)id label:(NSString *)label recipientKeys:(NSArray<NSString *> *)recipientKeys routingKeys:(NSArray<NSString *> *)routingKeys imageUrl:(NSString * _Nullable)imageUrl __attribute__((swift_name("init(type:id:label:recipientKeys:routingKeys:imageUrl:)"))) __attribute__((objc_designated_initializer));
- (NSString *)component1 __attribute__((swift_name("component1()")));
- (NSString *)component2 __attribute__((swift_name("component2()")));
- (NSString *)component3 __attribute__((swift_name("component3()")));
- (NSArray<NSString *> *)component4 __attribute__((swift_name("component4()")));
- (NSArray<NSString *> *)component5 __attribute__((swift_name("component5()")));
- (NSString * _Nullable)component6 __attribute__((swift_name("component6()")));
- (Ssi_agentInvitation *)doCopyType:(NSString *)type id:(NSString *)id label:(NSString *)label recipientKeys:(NSArray<NSString *> *)recipientKeys routingKeys:(NSArray<NSString *> *)routingKeys imageUrl:(NSString * _Nullable)imageUrl __attribute__((swift_name("doCopy(type:id:label:recipientKeys:routingKeys:imageUrl:)")));
- (BOOL)isEqual:(id _Nullable)other __attribute__((swift_name("isEqual(_:)")));
- (NSUInteger)hash __attribute__((swift_name("hash()")));
- (NSString *)description __attribute__((swift_name("description()")));
@property (readonly) NSString *id __attribute__((swift_name("id")));
@property (readonly) NSString * _Nullable imageUrl __attribute__((swift_name("imageUrl")));
@property (readonly) NSString *label __attribute__((swift_name("label")));
@property (readonly) NSArray<NSString *> *recipientKeys __attribute__((swift_name("recipientKeys")));
@property (readonly) NSArray<NSString *> *routingKeys __attribute__((swift_name("routingKeys")));
@property (readonly) NSString *type __attribute__((swift_name("type")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("Invitation.Companion")))
@interface Ssi_agentInvitationCompanion : Ssi_agentBase
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
+ (instancetype)companion __attribute__((swift_name("init()")));
- (id<Ssi_agentKotlinx_serialization_coreKSerializer>)serializer __attribute__((swift_name("serializer()")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("PublicKey")))
@interface Ssi_agentPublicKey : Ssi_agentBase
- (instancetype)initWithId:(NSString *)id type:(NSString *)type controller:(NSString *)controller publicKeyBase58:(NSString *)publicKeyBase58 __attribute__((swift_name("init(id:type:controller:publicKeyBase58:)"))) __attribute__((objc_designated_initializer));
- (NSString *)component1 __attribute__((swift_name("component1()")));
- (NSString *)component2 __attribute__((swift_name("component2()")));
- (NSString *)component3 __attribute__((swift_name("component3()")));
- (NSString *)component4 __attribute__((swift_name("component4()")));
- (Ssi_agentPublicKey *)doCopyId:(NSString *)id type:(NSString *)type controller:(NSString *)controller publicKeyBase58:(NSString *)publicKeyBase58 __attribute__((swift_name("doCopy(id:type:controller:publicKeyBase58:)")));
- (BOOL)isEqual:(id _Nullable)other __attribute__((swift_name("isEqual(_:)")));
- (NSUInteger)hash __attribute__((swift_name("hash()")));
- (NSString *)description __attribute__((swift_name("description()")));
@property (readonly) NSString *controller __attribute__((swift_name("controller")));
@property (readonly) NSString *id __attribute__((swift_name("id")));
@property (readonly) NSString *publicKeyBase58 __attribute__((swift_name("publicKeyBase58")));
@property (readonly) NSString *type __attribute__((swift_name("type")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("PublicKey.Companion")))
@interface Ssi_agentPublicKeyCompanion : Ssi_agentBase
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
+ (instancetype)companion __attribute__((swift_name("init()")));
- (id<Ssi_agentKotlinx_serialization_coreKSerializer>)serializer __attribute__((swift_name("serializer()")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("Attribute")))
@interface Ssi_agentAttribute : Ssi_agentBase
- (instancetype)initWithName:(NSString *)name mimeType:(NSString *)mimeType value:(NSString *)value __attribute__((swift_name("init(name:mimeType:value:)"))) __attribute__((objc_designated_initializer));
- (NSString *)component1 __attribute__((swift_name("component1()")));
- (NSString *)component2 __attribute__((swift_name("component2()")));
- (NSString *)component3 __attribute__((swift_name("component3()")));
- (Ssi_agentAttribute *)doCopyName:(NSString *)name mimeType:(NSString *)mimeType value:(NSString *)value __attribute__((swift_name("doCopy(name:mimeType:value:)")));
- (BOOL)isEqual:(id _Nullable)other __attribute__((swift_name("isEqual(_:)")));
- (NSUInteger)hash __attribute__((swift_name("hash()")));
- (NSString *)description __attribute__((swift_name("description()")));
@property (readonly) NSString *mimeType __attribute__((swift_name("mimeType")));
@property (readonly) NSString *name __attribute__((swift_name("name")));
@property (readonly) NSString *value __attribute__((swift_name("value")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("Attribute.Companion")))
@interface Ssi_agentAttributeCompanion : Ssi_agentBase
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
+ (instancetype)companion __attribute__((swift_name("init()")));
- (id<Ssi_agentKotlinx_serialization_coreKSerializer>)serializer __attribute__((swift_name("serializer()")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("CredentialContainer")))
@interface Ssi_agentCredentialContainer : Ssi_agentBase
- (instancetype)initWithType:(NSString *)type id:(NSString *)id credentialsAttach:(NSArray<Ssi_agentAttach *> *)credentialsAttach thread:(Ssi_agentThread *)thread comment:(NSString * _Nullable)comment __attribute__((swift_name("init(type:id:credentialsAttach:thread:comment:)"))) __attribute__((objc_designated_initializer));
- (NSString *)component1 __attribute__((swift_name("component1()")));
- (NSString *)component2 __attribute__((swift_name("component2()")));
- (NSArray<Ssi_agentAttach *> *)component3 __attribute__((swift_name("component3()")));
- (Ssi_agentThread *)component4 __attribute__((swift_name("component4()")));
- (NSString * _Nullable)component5 __attribute__((swift_name("component5()")));
- (Ssi_agentCredentialContainer *)doCopyType:(NSString *)type id:(NSString *)id credentialsAttach:(NSArray<Ssi_agentAttach *> *)credentialsAttach thread:(Ssi_agentThread *)thread comment:(NSString * _Nullable)comment __attribute__((swift_name("doCopy(type:id:credentialsAttach:thread:comment:)")));
- (BOOL)isEqual:(id _Nullable)other __attribute__((swift_name("isEqual(_:)")));
- (NSUInteger)hash __attribute__((swift_name("hash()")));
- (NSString *)description __attribute__((swift_name("description()")));
@property (readonly) NSString * _Nullable comment __attribute__((swift_name("comment")));
@property (readonly) NSArray<Ssi_agentAttach *> *credentialsAttach __attribute__((swift_name("credentialsAttach")));
@property (readonly) NSString *id __attribute__((swift_name("id")));
@property (readonly) Ssi_agentThread *thread __attribute__((swift_name("thread")));
@property (readonly) NSString *type __attribute__((swift_name("type")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("CredentialContainer.Companion")))
@interface Ssi_agentCredentialContainerCompanion : Ssi_agentBase
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
+ (instancetype)companion __attribute__((swift_name("init()")));
- (id<Ssi_agentKotlinx_serialization_coreKSerializer>)serializer __attribute__((swift_name("serializer()")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("CredentialOfferContainer")))
@interface Ssi_agentCredentialOfferContainer : Ssi_agentBase
- (instancetype)initWithType:(NSString *)type id:(NSString *)id credentialPreview:(Ssi_agentCredentialPreview *)credentialPreview offersAttach:(NSArray<Ssi_agentAttach *> *)offersAttach comment:(NSString * _Nullable)comment __attribute__((swift_name("init(type:id:credentialPreview:offersAttach:comment:)"))) __attribute__((objc_designated_initializer));
- (NSString *)component1 __attribute__((swift_name("component1()")));
- (NSString *)component2 __attribute__((swift_name("component2()")));
- (Ssi_agentCredentialPreview *)component3 __attribute__((swift_name("component3()")));
- (NSArray<Ssi_agentAttach *> *)component4 __attribute__((swift_name("component4()")));
- (NSString * _Nullable)component5 __attribute__((swift_name("component5()")));
- (Ssi_agentCredentialOfferContainer *)doCopyType:(NSString *)type id:(NSString *)id credentialPreview:(Ssi_agentCredentialPreview *)credentialPreview offersAttach:(NSArray<Ssi_agentAttach *> *)offersAttach comment:(NSString * _Nullable)comment __attribute__((swift_name("doCopy(type:id:credentialPreview:offersAttach:comment:)")));
- (BOOL)isEqual:(id _Nullable)other __attribute__((swift_name("isEqual(_:)")));
- (NSUInteger)hash __attribute__((swift_name("hash()")));
- (NSString *)description __attribute__((swift_name("description()")));
@property (readonly) NSString * _Nullable comment __attribute__((swift_name("comment")));
@property (readonly) Ssi_agentCredentialPreview *credentialPreview __attribute__((swift_name("credentialPreview")));
@property (readonly) NSString *id __attribute__((swift_name("id")));
@property (readonly) NSArray<Ssi_agentAttach *> *offersAttach __attribute__((swift_name("offersAttach")));
@property (readonly) NSString *type __attribute__((swift_name("type")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("CredentialOfferContainer.Companion")))
@interface Ssi_agentCredentialOfferContainerCompanion : Ssi_agentBase
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
+ (instancetype)companion __attribute__((swift_name("init()")));
- (id<Ssi_agentKotlinx_serialization_coreKSerializer>)serializer __attribute__((swift_name("serializer()")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("CredentialPreview")))
@interface Ssi_agentCredentialPreview : Ssi_agentBase
- (instancetype)initWithType:(NSString *)type id:(NSString * _Nullable)id attributes:(NSArray<Ssi_agentAttribute *> *)attributes __attribute__((swift_name("init(type:id:attributes:)"))) __attribute__((objc_designated_initializer));
- (NSString *)component1 __attribute__((swift_name("component1()")));
- (NSString * _Nullable)component2 __attribute__((swift_name("component2()")));
- (NSArray<Ssi_agentAttribute *> *)component3 __attribute__((swift_name("component3()")));
- (Ssi_agentCredentialPreview *)doCopyType:(NSString *)type id:(NSString * _Nullable)id attributes:(NSArray<Ssi_agentAttribute *> *)attributes __attribute__((swift_name("doCopy(type:id:attributes:)")));
- (BOOL)isEqual:(id _Nullable)other __attribute__((swift_name("isEqual(_:)")));
- (NSUInteger)hash __attribute__((swift_name("hash()")));
- (NSString *)description __attribute__((swift_name("description()")));
@property (readonly) NSArray<Ssi_agentAttribute *> *attributes __attribute__((swift_name("attributes")));
@property (readonly) NSString * _Nullable id __attribute__((swift_name("id")));
@property (readonly) NSString *type __attribute__((swift_name("type")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("CredentialPreview.Companion")))
@interface Ssi_agentCredentialPreviewCompanion : Ssi_agentBase
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
+ (instancetype)companion __attribute__((swift_name("init()")));
- (id<Ssi_agentKotlinx_serialization_coreKSerializer>)serializer __attribute__((swift_name("serializer()")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("CredentialRequestContainer")))
@interface Ssi_agentCredentialRequestContainer : Ssi_agentBase
- (instancetype)initWithType:(NSString *)type id:(NSString *)id thread:(Ssi_agentThread *)thread requestsAttach:(NSArray<Ssi_agentAttach *> *)requestsAttach comment:(NSString * _Nullable)comment __attribute__((swift_name("init(type:id:thread:requestsAttach:comment:)"))) __attribute__((objc_designated_initializer));
- (NSString *)component1 __attribute__((swift_name("component1()")));
- (NSString *)component2 __attribute__((swift_name("component2()")));
- (Ssi_agentThread *)component3 __attribute__((swift_name("component3()")));
- (NSArray<Ssi_agentAttach *> *)component4 __attribute__((swift_name("component4()")));
- (NSString * _Nullable)component5 __attribute__((swift_name("component5()")));
- (Ssi_agentCredentialRequestContainer *)doCopyType:(NSString *)type id:(NSString *)id thread:(Ssi_agentThread *)thread requestsAttach:(NSArray<Ssi_agentAttach *> *)requestsAttach comment:(NSString * _Nullable)comment __attribute__((swift_name("doCopy(type:id:thread:requestsAttach:comment:)")));
- (BOOL)isEqual:(id _Nullable)other __attribute__((swift_name("isEqual(_:)")));
- (NSUInteger)hash __attribute__((swift_name("hash()")));
- (NSString *)description __attribute__((swift_name("description()")));
@property (readonly) NSString * _Nullable comment __attribute__((swift_name("comment")));
@property (readonly) NSString *id __attribute__((swift_name("id")));
@property (readonly) NSArray<Ssi_agentAttach *> *requestsAttach __attribute__((swift_name("requestsAttach")));
@property (readonly) Ssi_agentThread *thread __attribute__((swift_name("thread")));
@property (readonly) NSString *type __attribute__((swift_name("type")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("CredentialRequestContainer.Companion")))
@interface Ssi_agentCredentialRequestContainerCompanion : Ssi_agentBase
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
+ (instancetype)companion __attribute__((swift_name("init()")));
- (id<Ssi_agentKotlinx_serialization_coreKSerializer>)serializer __attribute__((swift_name("serializer()")));
@end;

__attribute__((swift_name("Credential")))
@protocol Ssi_agentCredential
@required
@end;

__attribute__((swift_name("CredentialDefinition")))
@protocol Ssi_agentCredentialDefinition
@required
@end;

__attribute__((swift_name("CredentialDefinitionId")))
@protocol Ssi_agentCredentialDefinitionId
@required
@end;

__attribute__((swift_name("CredentialOffer")))
@protocol Ssi_agentCredentialOffer
@required
@end;

__attribute__((swift_name("CredentialRequest")))
@protocol Ssi_agentCredentialRequest
@required
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("CredentialRequestInfo")))
@interface Ssi_agentCredentialRequestInfo : Ssi_agentBase
- (instancetype)initWithCredentialRequest:(id<Ssi_agentCredentialRequest>)credentialRequest credentialRequestMetadata:(id<Ssi_agentCredentialRequestMetadata>)credentialRequestMetadata __attribute__((swift_name("init(credentialRequest:credentialRequestMetadata:)"))) __attribute__((objc_designated_initializer));
- (id<Ssi_agentCredentialRequest>)component1 __attribute__((swift_name("component1()")));
- (id<Ssi_agentCredentialRequestMetadata>)component2 __attribute__((swift_name("component2()")));
- (Ssi_agentCredentialRequestInfo *)doCopyCredentialRequest:(id<Ssi_agentCredentialRequest>)credentialRequest credentialRequestMetadata:(id<Ssi_agentCredentialRequestMetadata>)credentialRequestMetadata __attribute__((swift_name("doCopy(credentialRequest:credentialRequestMetadata:)")));
- (BOOL)isEqual:(id _Nullable)other __attribute__((swift_name("isEqual(_:)")));
- (NSUInteger)hash __attribute__((swift_name("hash()")));
- (NSString *)description __attribute__((swift_name("description()")));
@property (readonly) id<Ssi_agentCredentialRequest> credentialRequest __attribute__((swift_name("credentialRequest")));
@property (readonly) id<Ssi_agentCredentialRequestMetadata> credentialRequestMetadata __attribute__((swift_name("credentialRequestMetadata")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("CredentialRequestInfo.Companion")))
@interface Ssi_agentCredentialRequestInfoCompanion : Ssi_agentBase
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
+ (instancetype)companion __attribute__((swift_name("init()")));
- (id<Ssi_agentKotlinx_serialization_coreKSerializer>)serializer __attribute__((swift_name("serializer()")));
@end;

__attribute__((swift_name("CredentialRequestMetadata")))
@protocol Ssi_agentCredentialRequestMetadata
@required
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("Forward")))
@interface Ssi_agentForward : Ssi_agentBase
- (instancetype)initWithType:(NSString *)type id:(NSString *)id to:(NSString *)to msg:(Ssi_agentEncryptedEnvelop *)msg __attribute__((swift_name("init(type:id:to:msg:)"))) __attribute__((objc_designated_initializer));
- (NSString *)component1 __attribute__((swift_name("component1()")));
- (NSString *)component2 __attribute__((swift_name("component2()")));
- (NSString *)component3 __attribute__((swift_name("component3()")));
- (Ssi_agentEncryptedEnvelop *)component4 __attribute__((swift_name("component4()")));
- (Ssi_agentForward *)doCopyType:(NSString *)type id:(NSString *)id to:(NSString *)to msg:(Ssi_agentEncryptedEnvelop *)msg __attribute__((swift_name("doCopy(type:id:to:msg:)")));
- (BOOL)isEqual:(id _Nullable)other __attribute__((swift_name("isEqual(_:)")));
- (NSUInteger)hash __attribute__((swift_name("hash()")));
- (NSString *)description __attribute__((swift_name("description()")));
@property (readonly) NSString *id __attribute__((swift_name("id")));
@property (readonly) Ssi_agentEncryptedEnvelop *msg __attribute__((swift_name("msg")));
@property (readonly) NSString *to __attribute__((swift_name("to")));
@property (readonly) NSString *type __attribute__((swift_name("type")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("Forward.Companion")))
@interface Ssi_agentForwardCompanion : Ssi_agentBase
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
+ (instancetype)companion __attribute__((swift_name("init()")));
- (id<Ssi_agentKotlinx_serialization_coreKSerializer>)serializer __attribute__((swift_name("serializer()")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("Attach")))
@interface Ssi_agentAttach : Ssi_agentBase
- (instancetype)initWithId:(NSString *)id mimeType:(NSString *)mimeType data:(Ssi_agentData *)data __attribute__((swift_name("init(id:mimeType:data:)"))) __attribute__((objc_designated_initializer));
- (NSString *)component1 __attribute__((swift_name("component1()")));
- (NSString *)component2 __attribute__((swift_name("component2()")));
- (Ssi_agentData *)component3 __attribute__((swift_name("component3()")));
- (Ssi_agentAttach *)doCopyId:(NSString *)id mimeType:(NSString *)mimeType data:(Ssi_agentData *)data __attribute__((swift_name("doCopy(id:mimeType:data:)")));
- (BOOL)isEqual:(id _Nullable)other __attribute__((swift_name("isEqual(_:)")));
- (NSUInteger)hash __attribute__((swift_name("hash()")));
- (NSString *)description __attribute__((swift_name("description()")));
@property (readonly) Ssi_agentData *data __attribute__((swift_name("data")));
@property (readonly) NSString *id __attribute__((swift_name("id")));
@property (readonly) NSString *mimeType __attribute__((swift_name("mimeType")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("Attach.Companion")))
@interface Ssi_agentAttachCompanion : Ssi_agentBase
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
+ (instancetype)companion __attribute__((swift_name("init()")));
- (id<Ssi_agentKotlinx_serialization_coreKSerializer>)serializer __attribute__((swift_name("serializer()")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("Data")))
@interface Ssi_agentData : Ssi_agentBase
- (instancetype)initWithBase64:(NSString *)base64 __attribute__((swift_name("init(base64:)"))) __attribute__((objc_designated_initializer));
- (NSString *)component1 __attribute__((swift_name("component1()")));
- (Ssi_agentData *)doCopyBase64:(NSString *)base64 __attribute__((swift_name("doCopy(base64:)")));
- (BOOL)isEqual:(id _Nullable)other __attribute__((swift_name("isEqual(_:)")));
- (NSUInteger)hash __attribute__((swift_name("hash()")));
- (NSString *)description __attribute__((swift_name("description()")));
@property (readonly) NSString *base64 __attribute__((swift_name("base64")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("Data.Companion")))
@interface Ssi_agentDataCompanion : Ssi_agentBase
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
+ (instancetype)companion __attribute__((swift_name("init()")));
- (id<Ssi_agentKotlinx_serialization_coreKSerializer>)serializer __attribute__((swift_name("serializer()")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("ProblemReport")))
@interface Ssi_agentProblemReport : Ssi_agentBase
- (instancetype)init __attribute__((swift_name("init()"))) __attribute__((objc_designated_initializer));
+ (instancetype)new __attribute__((availability(swift, unavailable, message="use object initializers instead")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("Service")))
@interface Ssi_agentService : Ssi_agentBase
- (instancetype)initWithId:(NSString *)id type:(NSString *)type priority:(int32_t)priority recipientKeys:(NSArray<NSString *> *)recipientKeys serviceEndpoint:(NSString *)serviceEndpoint __attribute__((swift_name("init(id:type:priority:recipientKeys:serviceEndpoint:)"))) __attribute__((objc_designated_initializer));
- (NSString *)component1 __attribute__((swift_name("component1()")));
- (NSString *)component2 __attribute__((swift_name("component2()")));
- (int32_t)component3 __attribute__((swift_name("component3()")));
- (NSArray<NSString *> *)component4 __attribute__((swift_name("component4()")));
- (NSString *)component5 __attribute__((swift_name("component5()")));
- (Ssi_agentService *)doCopyId:(NSString *)id type:(NSString *)type priority:(int32_t)priority recipientKeys:(NSArray<NSString *> *)recipientKeys serviceEndpoint:(NSString *)serviceEndpoint __attribute__((swift_name("doCopy(id:type:priority:recipientKeys:serviceEndpoint:)")));
- (BOOL)isEqual:(id _Nullable)other __attribute__((swift_name("isEqual(_:)")));
- (NSUInteger)hash __attribute__((swift_name("hash()")));
- (NSString *)description __attribute__((swift_name("description()")));
@property (readonly) NSString *id __attribute__((swift_name("id")));
@property (readonly) int32_t priority __attribute__((swift_name("priority")));
@property (readonly) NSArray<NSString *> *recipientKeys __attribute__((swift_name("recipientKeys")));
@property (readonly) NSString *serviceEndpoint __attribute__((swift_name("serviceEndpoint")));
@property (readonly) NSString *type __attribute__((swift_name("type")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("Service.Companion")))
@interface Ssi_agentServiceCompanion : Ssi_agentBase
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
+ (instancetype)companion __attribute__((swift_name("init()")));
- (id<Ssi_agentKotlinx_serialization_coreKSerializer>)serializer __attribute__((swift_name("serializer()")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("Thread")))
@interface Ssi_agentThread : Ssi_agentBase
- (instancetype)initWithThid:(NSString *)thid __attribute__((swift_name("init(thid:)"))) __attribute__((objc_designated_initializer));
- (NSString *)component1 __attribute__((swift_name("component1()")));
- (Ssi_agentThread *)doCopyThid:(NSString *)thid __attribute__((swift_name("doCopy(thid:)")));
- (BOOL)isEqual:(id _Nullable)other __attribute__((swift_name("isEqual(_:)")));
- (NSUInteger)hash __attribute__((swift_name("hash()")));
- (NSString *)description __attribute__((swift_name("description()")));
@property (readonly) NSString *thid __attribute__((swift_name("thid")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("Thread.Companion")))
@interface Ssi_agentThreadCompanion : Ssi_agentBase
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
+ (instancetype)companion __attribute__((swift_name("init()")));
- (id<Ssi_agentKotlinx_serialization_coreKSerializer>)serializer __attribute__((swift_name("serializer()")));
@end;

__attribute__((swift_name("Presentation")))
@protocol Ssi_agentPresentation
@required
@end;

__attribute__((swift_name("PresentationRequest")))
@protocol Ssi_agentPresentationRequest
@required
@end;

__attribute__((swift_name("Schema")))
@protocol Ssi_agentSchema
@required
@end;

__attribute__((swift_name("SchemaId")))
@protocol Ssi_agentSchemaId
@required
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("PresentationContainer")))
@interface Ssi_agentPresentationContainer : Ssi_agentBase
- (instancetype)initWithType:(NSString *)type id:(NSString *)id presentationAttach:(NSArray<Ssi_agentAttach *> *)presentationAttach thread:(Ssi_agentThread *)thread comment:(NSString * _Nullable)comment __attribute__((swift_name("init(type:id:presentationAttach:thread:comment:)"))) __attribute__((objc_designated_initializer));
- (NSString *)component1 __attribute__((swift_name("component1()")));
- (NSString *)component2 __attribute__((swift_name("component2()")));
- (NSArray<Ssi_agentAttach *> *)component3 __attribute__((swift_name("component3()")));
- (Ssi_agentThread *)component4 __attribute__((swift_name("component4()")));
- (NSString * _Nullable)component5 __attribute__((swift_name("component5()")));
- (Ssi_agentPresentationContainer *)doCopyType:(NSString *)type id:(NSString *)id presentationAttach:(NSArray<Ssi_agentAttach *> *)presentationAttach thread:(Ssi_agentThread *)thread comment:(NSString * _Nullable)comment __attribute__((swift_name("doCopy(type:id:presentationAttach:thread:comment:)")));
- (BOOL)isEqual:(id _Nullable)other __attribute__((swift_name("isEqual(_:)")));
- (NSUInteger)hash __attribute__((swift_name("hash()")));
- (NSString *)description __attribute__((swift_name("description()")));
@property (readonly) NSString * _Nullable comment __attribute__((swift_name("comment")));
@property (readonly) NSString *id __attribute__((swift_name("id")));
@property (readonly) NSArray<Ssi_agentAttach *> *presentationAttach __attribute__((swift_name("presentationAttach")));
@property (readonly) Ssi_agentThread *thread __attribute__((swift_name("thread")));
@property (readonly) NSString *type __attribute__((swift_name("type")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("PresentationContainer.Companion")))
@interface Ssi_agentPresentationContainerCompanion : Ssi_agentBase
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
+ (instancetype)companion __attribute__((swift_name("init()")));
- (id<Ssi_agentKotlinx_serialization_coreKSerializer>)serializer __attribute__((swift_name("serializer()")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("PresentationRequestContainer")))
@interface Ssi_agentPresentationRequestContainer : Ssi_agentBase
- (instancetype)initWithType:(NSString *)type id:(NSString *)id presentationRequestAttach:(NSArray<Ssi_agentAttach *> *)presentationRequestAttach comment:(NSString * _Nullable)comment __attribute__((swift_name("init(type:id:presentationRequestAttach:comment:)"))) __attribute__((objc_designated_initializer));
- (NSString *)component1 __attribute__((swift_name("component1()")));
- (NSString *)component2 __attribute__((swift_name("component2()")));
- (NSArray<Ssi_agentAttach *> *)component3 __attribute__((swift_name("component3()")));
- (NSString * _Nullable)component4 __attribute__((swift_name("component4()")));
- (Ssi_agentPresentationRequestContainer *)doCopyType:(NSString *)type id:(NSString *)id presentationRequestAttach:(NSArray<Ssi_agentAttach *> *)presentationRequestAttach comment:(NSString * _Nullable)comment __attribute__((swift_name("doCopy(type:id:presentationRequestAttach:comment:)")));
- (BOOL)isEqual:(id _Nullable)other __attribute__((swift_name("isEqual(_:)")));
- (NSUInteger)hash __attribute__((swift_name("hash()")));
- (NSString *)description __attribute__((swift_name("description()")));
@property (readonly) NSString * _Nullable comment __attribute__((swift_name("comment")));
@property (readonly) NSString *id __attribute__((swift_name("id")));
@property (readonly) NSArray<Ssi_agentAttach *> *presentationRequestAttach __attribute__((swift_name("presentationRequestAttach")));
@property (readonly) NSString *type __attribute__((swift_name("type")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("PresentationRequestContainer.Companion")))
@interface Ssi_agentPresentationRequestContainerCompanion : Ssi_agentBase
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
+ (instancetype)companion __attribute__((swift_name("init()")));
- (id<Ssi_agentKotlinx_serialization_coreKSerializer>)serializer __attribute__((swift_name("serializer()")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("EncryptedEnvelop")))
@interface Ssi_agentEncryptedEnvelop : Ssi_agentBase
- (instancetype)initWithProtected:(NSString *)protected_ iv:(NSString *)iv ciphertext:(NSString *)ciphertext tag:(NSString *)tag __attribute__((swift_name("init(protected:iv:ciphertext:tag:)"))) __attribute__((objc_designated_initializer));
- (NSString *)component1 __attribute__((swift_name("component1()")));
- (NSString *)component2 __attribute__((swift_name("component2()")));
- (NSString *)component3 __attribute__((swift_name("component3()")));
- (NSString *)component4 __attribute__((swift_name("component4()")));
- (Ssi_agentEncryptedEnvelop *)doCopyProtected:(NSString *)protected_ iv:(NSString *)iv ciphertext:(NSString *)ciphertext tag:(NSString *)tag __attribute__((swift_name("doCopy(protected:iv:ciphertext:tag:)")));
- (BOOL)isEqual:(id _Nullable)other __attribute__((swift_name("isEqual(_:)")));
- (NSUInteger)hash __attribute__((swift_name("hash()")));
- (NSString *)description __attribute__((swift_name("description()")));
@property (readonly) NSString *ciphertext __attribute__((swift_name("ciphertext")));
@property (readonly) NSString *iv __attribute__((swift_name("iv")));
@property (readonly, getter=protected) NSString *protected_ __attribute__((swift_name("protected_")));
@property (readonly) NSString *tag __attribute__((swift_name("tag")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("EncryptedEnvelop.Companion")))
@interface Ssi_agentEncryptedEnvelopCompanion : Ssi_agentBase
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
+ (instancetype)companion __attribute__((swift_name("init()")));
- (id<Ssi_agentKotlinx_serialization_coreKSerializer>)serializer __attribute__((swift_name("serializer()")));
@end;

__attribute__((swift_name("RevocationRegistryDefinition")))
@protocol Ssi_agentRevocationRegistryDefinition
@required
@end;

__attribute__((swift_name("State")))
@protocol Ssi_agentState
@required
@end;

__attribute__((swift_name("StateMachine")))
@protocol Ssi_agentStateMachine
@required
- (Ssi_agentKotlinPair<id<Ssi_agentState>, id<Ssi_agentAction>> *)getNextStateAndActionCurrentState:(id<Ssi_agentState>)currentState message:(Ssi_agentMessage *)message __attribute__((swift_name("getNextStateAndAction(currentState:message:)")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("CredentialIssuenceState")))
@interface Ssi_agentCredentialIssuenceState : Ssi_agentKotlinEnum<Ssi_agentCredentialIssuenceState *>
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
- (instancetype)initWithName:(NSString *)name ordinal:(int32_t)ordinal __attribute__((swift_name("init(name:ordinal:)"))) __attribute__((objc_designated_initializer)) __attribute__((unavailable));
@property (class, readonly) Ssi_agentCredentialIssuenceState *proposalSent __attribute__((swift_name("proposalSent")));
@property (class, readonly) Ssi_agentCredentialIssuenceState *proposalReceived __attribute__((swift_name("proposalReceived")));
@property (class, readonly) Ssi_agentCredentialIssuenceState *offerSent __attribute__((swift_name("offerSent")));
@property (class, readonly) Ssi_agentCredentialIssuenceState *offerReceived __attribute__((swift_name("offerReceived")));
@property (class, readonly) Ssi_agentCredentialIssuenceState *credentialSent __attribute__((swift_name("credentialSent")));
@property (class, readonly) Ssi_agentCredentialIssuenceState *credentialReceived __attribute__((swift_name("credentialReceived")));
@property (class, readonly) Ssi_agentCredentialIssuenceState *done __attribute__((swift_name("done")));
+ (Ssi_agentKotlinArray<Ssi_agentCredentialIssuenceState *> *)values __attribute__((swift_name("values()")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("CredentialIssuenceStateMachine")))
@interface Ssi_agentCredentialIssuenceStateMachine : Ssi_agentBase <Ssi_agentStateMachine>
- (instancetype)init __attribute__((swift_name("init()"))) __attribute__((objc_designated_initializer));
+ (instancetype)new __attribute__((availability(swift, unavailable, message="use object initializers instead")));
- (Ssi_agentKotlinPair<id<Ssi_agentState>, id<Ssi_agentAction>> *)getNextStateAndActionCurrentState:(id<Ssi_agentState>)currentState message:(Ssi_agentMessage *)message __attribute__((swift_name("getNextStateAndAction(currentState:message:)")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("CredentialVerificationState")))
@interface Ssi_agentCredentialVerificationState : Ssi_agentKotlinEnum<Ssi_agentCredentialVerificationState *>
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
- (instancetype)initWithName:(NSString *)name ordinal:(int32_t)ordinal __attribute__((swift_name("init(name:ordinal:)"))) __attribute__((objc_designated_initializer)) __attribute__((unavailable));
@property (class, readonly) Ssi_agentCredentialVerificationState *proposalSent __attribute__((swift_name("proposalSent")));
@property (class, readonly) Ssi_agentCredentialVerificationState *proposalReceived __attribute__((swift_name("proposalReceived")));
@property (class, readonly) Ssi_agentCredentialVerificationState *requestSent __attribute__((swift_name("requestSent")));
@property (class, readonly) Ssi_agentCredentialVerificationState *requestReceived __attribute__((swift_name("requestReceived")));
@property (class, readonly) Ssi_agentCredentialVerificationState *presentationSent __attribute__((swift_name("presentationSent")));
@property (class, readonly) Ssi_agentCredentialVerificationState *presentationReceived __attribute__((swift_name("presentationReceived")));
@property (class, readonly) Ssi_agentCredentialVerificationState *rejectSent __attribute__((swift_name("rejectSent")));
@property (class, readonly) Ssi_agentCredentialVerificationState *rejectReceived __attribute__((swift_name("rejectReceived")));
@property (class, readonly) Ssi_agentCredentialVerificationState *done __attribute__((swift_name("done")));
+ (Ssi_agentKotlinArray<Ssi_agentCredentialVerificationState *> *)values __attribute__((swift_name("values()")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("CredentialVerificationStateMachine")))
@interface Ssi_agentCredentialVerificationStateMachine : Ssi_agentBase <Ssi_agentStateMachine>
- (instancetype)init __attribute__((swift_name("init()"))) __attribute__((objc_designated_initializer));
+ (instancetype)new __attribute__((availability(swift, unavailable, message="use object initializers instead")));
- (Ssi_agentKotlinPair<id<Ssi_agentState>, id<Ssi_agentAction>> *)getNextStateAndActionCurrentState:(id<Ssi_agentState>)currentState message:(Ssi_agentMessage *)message __attribute__((swift_name("getNextStateAndAction(currentState:message:)")));
@end;

__attribute__((swift_name("DidExchangeState")))
@protocol Ssi_agentDidExchangeState <Ssi_agentState>
@required
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("DidExchangeStateAbandoned")))
@interface Ssi_agentDidExchangeStateAbandoned : Ssi_agentBase <Ssi_agentDidExchangeState>
- (instancetype)init __attribute__((swift_name("init()"))) __attribute__((objc_designated_initializer));
+ (instancetype)new __attribute__((availability(swift, unavailable, message="use object initializers instead")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("DidExchangeStateCompleted")))
@interface Ssi_agentDidExchangeStateCompleted : Ssi_agentBase <Ssi_agentDidExchangeState>
- (instancetype)init __attribute__((swift_name("init()"))) __attribute__((objc_designated_initializer));
+ (instancetype)new __attribute__((availability(swift, unavailable, message="use object initializers instead")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("DidExchangeStateInvitationReceived")))
@interface Ssi_agentDidExchangeStateInvitationReceived : Ssi_agentBase <Ssi_agentDidExchangeState>
- (instancetype)init __attribute__((swift_name("init()"))) __attribute__((objc_designated_initializer));
+ (instancetype)new __attribute__((availability(swift, unavailable, message="use object initializers instead")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("DidExchangeStateInvitationSent")))
@interface Ssi_agentDidExchangeStateInvitationSent : Ssi_agentBase <Ssi_agentDidExchangeState>
- (instancetype)init __attribute__((swift_name("init()"))) __attribute__((objc_designated_initializer));
+ (instancetype)new __attribute__((availability(swift, unavailable, message="use object initializers instead")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("DidExchangeStateMachine")))
@interface Ssi_agentDidExchangeStateMachine : Ssi_agentBase <Ssi_agentStateMachine>
- (instancetype)init __attribute__((swift_name("init()"))) __attribute__((objc_designated_initializer));
+ (instancetype)new __attribute__((availability(swift, unavailable, message="use object initializers instead")));
- (Ssi_agentKotlinPair<id<Ssi_agentState>, id<Ssi_agentAction>> *)getNextStateAndActionCurrentState:(id<Ssi_agentState>)currentState message:(Ssi_agentMessage *)message __attribute__((swift_name("getNextStateAndAction(currentState:message:)")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("DidExchangeStateRequestReceived")))
@interface Ssi_agentDidExchangeStateRequestReceived : Ssi_agentBase <Ssi_agentDidExchangeState>
- (instancetype)init __attribute__((swift_name("init()"))) __attribute__((objc_designated_initializer));
+ (instancetype)new __attribute__((availability(swift, unavailable, message="use object initializers instead")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("DidExchangeStateRequestSent")))
@interface Ssi_agentDidExchangeStateRequestSent : Ssi_agentBase <Ssi_agentDidExchangeState>
- (instancetype)init __attribute__((swift_name("init()"))) __attribute__((objc_designated_initializer));
+ (instancetype)new __attribute__((availability(swift, unavailable, message="use object initializers instead")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("DidExchangeStateResponseReceived")))
@interface Ssi_agentDidExchangeStateResponseReceived : Ssi_agentBase <Ssi_agentDidExchangeState>
- (instancetype)init __attribute__((swift_name("init()"))) __attribute__((objc_designated_initializer));
+ (instancetype)new __attribute__((availability(swift, unavailable, message="use object initializers instead")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("DidExchangeStateResponseSent")))
@interface Ssi_agentDidExchangeStateResponseSent : Ssi_agentBase <Ssi_agentDidExchangeState>
- (instancetype)init __attribute__((swift_name("init()"))) __attribute__((objc_designated_initializer));
+ (instancetype)new __attribute__((availability(swift, unavailable, message="use object initializers instead")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("DidExchangeStateStarted")))
@interface Ssi_agentDidExchangeStateStarted : Ssi_agentBase <Ssi_agentDidExchangeState>
- (instancetype)init __attribute__((swift_name("init()"))) __attribute__((objc_designated_initializer));
+ (instancetype)new __attribute__((availability(swift, unavailable, message="use object initializers instead")));
@end;

__attribute__((swift_name("Action")))
@protocol Ssi_agentAction
@required

/**
 @note This method converts instances of CancellationException to errors.
 Other uncaught Kotlin exceptions are fatal.
*/
- (void)performWithCompletionHandler:(void (^)(Ssi_agentActionResult * _Nullable, NSError * _Nullable))completionHandler __attribute__((swift_name("perform(completionHandler:)")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("ActionParams")))
@interface Ssi_agentActionParams : Ssi_agentBase
- (instancetype)initWithWalletConnector:(Ssi_agentWalletConnector *)walletConnector ledgerConnector:(id<Ssi_agentLedgerConnector>)ledgerConnector transport:(id<Ssi_agentTransport>)transport callbacks:(Ssi_agentCallbacks *)callbacks messageContext:(Ssi_agentMessageContext *)messageContext trustPingProcessor:(id<Ssi_agentTrustPingProcessor> _Nullable)trustPingProcessor trustPingTrackerService:(Ssi_agentTrustPingTrackerService * _Nullable)trustPingTrackerService __attribute__((swift_name("init(walletConnector:ledgerConnector:transport:callbacks:messageContext:trustPingProcessor:trustPingTrackerService:)"))) __attribute__((objc_designated_initializer));
- (Ssi_agentWalletConnector *)component1 __attribute__((swift_name("component1()")));
- (id<Ssi_agentLedgerConnector>)component2 __attribute__((swift_name("component2()")));
- (id<Ssi_agentTransport>)component3 __attribute__((swift_name("component3()")));
- (Ssi_agentCallbacks *)component4 __attribute__((swift_name("component4()")));
- (Ssi_agentMessageContext *)component5 __attribute__((swift_name("component5()")));
- (id<Ssi_agentTrustPingProcessor> _Nullable)component6 __attribute__((swift_name("component6()")));
- (Ssi_agentTrustPingTrackerService * _Nullable)component7 __attribute__((swift_name("component7()")));
- (Ssi_agentActionParams *)doCopyWalletConnector:(Ssi_agentWalletConnector *)walletConnector ledgerConnector:(id<Ssi_agentLedgerConnector>)ledgerConnector transport:(id<Ssi_agentTransport>)transport callbacks:(Ssi_agentCallbacks *)callbacks messageContext:(Ssi_agentMessageContext *)messageContext trustPingProcessor:(id<Ssi_agentTrustPingProcessor> _Nullable)trustPingProcessor trustPingTrackerService:(Ssi_agentTrustPingTrackerService * _Nullable)trustPingTrackerService __attribute__((swift_name("doCopy(walletConnector:ledgerConnector:transport:callbacks:messageContext:trustPingProcessor:trustPingTrackerService:)")));
- (BOOL)isEqual:(id _Nullable)other __attribute__((swift_name("isEqual(_:)")));
- (NSUInteger)hash __attribute__((swift_name("hash()")));
- (NSString *)description __attribute__((swift_name("description()")));
@property (readonly) Ssi_agentCallbacks *callbacks __attribute__((swift_name("callbacks")));
@property (readonly) id<Ssi_agentLedgerConnector> ledgerConnector __attribute__((swift_name("ledgerConnector")));
@property (readonly) Ssi_agentMessageContext *messageContext __attribute__((swift_name("messageContext")));
@property (readonly) id<Ssi_agentTransport> transport __attribute__((swift_name("transport")));
@property (readonly) id<Ssi_agentTrustPingProcessor> _Nullable trustPingProcessor __attribute__((swift_name("trustPingProcessor")));
@property (readonly) Ssi_agentTrustPingTrackerService * _Nullable trustPingTrackerService __attribute__((swift_name("trustPingTrackerService")));
@property (readonly) Ssi_agentWalletConnector *walletConnector __attribute__((swift_name("walletConnector")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("ActionResult")))
@interface Ssi_agentActionResult : Ssi_agentBase
- (instancetype)initWithConnection:(Ssi_agentConnection * _Nullable)connection trustPingSuccessful:(Ssi_agentBoolean * _Nullable)trustPingSuccessful __attribute__((swift_name("init(connection:trustPingSuccessful:)"))) __attribute__((objc_designated_initializer));
- (Ssi_agentConnection * _Nullable)component1 __attribute__((swift_name("component1()")));
- (Ssi_agentBoolean * _Nullable)component2 __attribute__((swift_name("component2()")));
- (Ssi_agentActionResult *)doCopyConnection:(Ssi_agentConnection * _Nullable)connection trustPingSuccessful:(Ssi_agentBoolean * _Nullable)trustPingSuccessful __attribute__((swift_name("doCopy(connection:trustPingSuccessful:)")));
- (BOOL)isEqual:(id _Nullable)other __attribute__((swift_name("isEqual(_:)")));
- (NSUInteger)hash __attribute__((swift_name("hash()")));
- (NSString *)description __attribute__((swift_name("description()")));
@property (readonly) Ssi_agentConnection * _Nullable connection __attribute__((swift_name("connection")));
@property (readonly) Ssi_agentBoolean * _Nullable trustPingSuccessful __attribute__((swift_name("trustPingSuccessful")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("ReceiveTrustPingResponseAction")))
@interface Ssi_agentReceiveTrustPingResponseAction : Ssi_agentBase <Ssi_agentAction>
- (instancetype)initWithActionParams:(Ssi_agentActionParams *)actionParams __attribute__((swift_name("init(actionParams:)"))) __attribute__((objc_designated_initializer));

/**
 @note This method converts instances of CancellationException to errors.
 Other uncaught Kotlin exceptions are fatal.
*/
- (void)performWithCompletionHandler:(void (^)(Ssi_agentActionResult * _Nullable, NSError * _Nullable))completionHandler __attribute__((swift_name("perform(completionHandler:)")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("SendTrustPingAction")))
@interface Ssi_agentSendTrustPingAction : Ssi_agentBase
- (instancetype)initWithWalletConnector:(Ssi_agentWalletConnector *)walletConnector transport:(id<Ssi_agentTransport>)transport trustPingTrackerService:(Ssi_agentTrustPingTrackerService *)trustPingTrackerService connection:(Ssi_agentConnection *)connection __attribute__((swift_name("init(walletConnector:transport:trustPingTrackerService:connection:)"))) __attribute__((objc_designated_initializer));

/**
 @note This method converts instances of CancellationException to errors.
 Other uncaught Kotlin exceptions are fatal.
*/
- (void)performWithCompletionHandler:(void (^)(Ssi_agentActionResult * _Nullable, NSError * _Nullable))completionHandler __attribute__((swift_name("perform(completionHandler:)")));
@property (readonly) id<Ssi_agentTransport> transport __attribute__((swift_name("transport")));
@property (readonly) Ssi_agentTrustPingTrackerService *trustPingTrackerService __attribute__((swift_name("trustPingTrackerService")));
@property (readonly) Ssi_agentWalletConnector *walletConnector __attribute__((swift_name("walletConnector")));
@end;

__attribute__((swift_name("CredentialVerificationAction")))
@protocol Ssi_agentCredentialVerificationAction <Ssi_agentAction>
@required
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("ReceivePresentationRequestAction")))
@interface Ssi_agentReceivePresentationRequestAction : Ssi_agentBase <Ssi_agentCredentialVerificationAction>
- (instancetype)initWithActionParams:(Ssi_agentActionParams *)actionParams __attribute__((swift_name("init(actionParams:)"))) __attribute__((objc_designated_initializer));

/**
 @note This method converts instances of CancellationException to errors.
 Other uncaught Kotlin exceptions are fatal.
*/
- (void)performWithCompletionHandler:(void (^)(Ssi_agentActionResult * _Nullable, NSError * _Nullable))completionHandler __attribute__((swift_name("perform(completionHandler:)")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("BuildForwardMessage")))
@interface Ssi_agentBuildForwardMessage : Ssi_agentBase
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
+ (instancetype)buildForwardMessage __attribute__((swift_name("init()")));
- (Ssi_agentForward *)buildForwardMessageMessageEnvelop:(Ssi_agentMessageEnvelop *)messageEnvelop inviterDid:(NSString *)inviterDid __attribute__((swift_name("buildForwardMessage(messageEnvelop:inviterDid:)")));
@end;

__attribute__((swift_name("DidExchangeAction")))
@protocol Ssi_agentDidExchangeAction <Ssi_agentAction>
@required
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("AbortConnection")))
@interface Ssi_agentAbortConnection : Ssi_agentBase <Ssi_agentDidExchangeAction>
- (instancetype)initWithWalletConnector:(Ssi_agentWalletConnector *)walletConnector connectionInitiatorController:(id<Ssi_agentConnectionInitiatorController>)connectionInitiatorController connectionId:(NSString *)connectionId __attribute__((swift_name("init(walletConnector:connectionInitiatorController:connectionId:)"))) __attribute__((objc_designated_initializer));

/**
 @note This method converts instances of CancellationException to errors.
 Other uncaught Kotlin exceptions are fatal.
*/
- (void)performWithCompletionHandler:(void (^)(Ssi_agentActionResult * _Nullable, NSError * _Nullable))completionHandler __attribute__((swift_name("perform(completionHandler:)")));
@property (readonly) Ssi_agentWalletConnector *walletConnector __attribute__((swift_name("walletConnector")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("ReceiveConnectionResponseAction")))
@interface Ssi_agentReceiveConnectionResponseAction : Ssi_agentBase <Ssi_agentDidExchangeAction>
- (instancetype)initWithActionParams:(Ssi_agentActionParams *)actionParams __attribute__((swift_name("init(actionParams:)"))) __attribute__((objc_designated_initializer));

/**
 @note This method converts instances of CancellationException to errors.
 Other uncaught Kotlin exceptions are fatal.
*/
- (void)performWithCompletionHandler:(void (^)(Ssi_agentActionResult * _Nullable, NSError * _Nullable))completionHandler __attribute__((swift_name("perform(completionHandler:)")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("ReceiveInvitationAction")))
@interface Ssi_agentReceiveInvitationAction : Ssi_agentBase <Ssi_agentDidExchangeAction>
- (instancetype)initWithWalletConnector:(Ssi_agentWalletConnector *)walletConnector transport:(id<Ssi_agentTransport>)transport connectionInitiatorController:(id<Ssi_agentConnectionInitiatorController>)connectionInitiatorController invitationUrl:(NSString *)invitationUrl __attribute__((swift_name("init(walletConnector:transport:connectionInitiatorController:invitationUrl:)"))) __attribute__((objc_designated_initializer));

/**
 @note This method converts instances of CancellationException to errors.
 Other uncaught Kotlin exceptions are fatal.
*/
- (void)performWithCompletionHandler:(void (^)(Ssi_agentActionResult * _Nullable, NSError * _Nullable))completionHandler __attribute__((swift_name("perform(completionHandler:)")));
@property (readonly) id<Ssi_agentConnectionInitiatorController> connectionInitiatorController __attribute__((swift_name("connectionInitiatorController")));
@property (readonly) id<Ssi_agentTransport> transport __attribute__((swift_name("transport")));
@property (readonly) Ssi_agentWalletConnector *walletConnector __attribute__((swift_name("walletConnector")));
@end;

__attribute__((swift_name("CredentialIssuenceAction")))
@protocol Ssi_agentCredentialIssuenceAction <Ssi_agentAction>
@required
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("ReceiveCredentialAction")))
@interface Ssi_agentReceiveCredentialAction : Ssi_agentBase <Ssi_agentCredentialIssuenceAction>
- (instancetype)initWithActionParams:(Ssi_agentActionParams *)actionParams __attribute__((swift_name("init(actionParams:)"))) __attribute__((objc_designated_initializer));

/**
 @note This method converts instances of CancellationException to errors.
 Other uncaught Kotlin exceptions are fatal.
*/
- (void)performWithCompletionHandler:(void (^)(Ssi_agentActionResult * _Nullable, NSError * _Nullable))completionHandler __attribute__((swift_name("perform(completionHandler:)")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("ReceiveCredentialOfferAction")))
@interface Ssi_agentReceiveCredentialOfferAction : Ssi_agentBase <Ssi_agentCredentialIssuenceAction>
- (instancetype)initWithActionParams:(Ssi_agentActionParams *)actionParams __attribute__((swift_name("init(actionParams:)"))) __attribute__((objc_designated_initializer));

/**
 @note This method converts instances of CancellationException to errors.
 Other uncaught Kotlin exceptions are fatal.
*/
- (void)performWithCompletionHandler:(void (^)(Ssi_agentActionResult * _Nullable, NSError * _Nullable))completionHandler __attribute__((swift_name("perform(completionHandler:)")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("MessagePacker")))
@interface Ssi_agentMessagePacker : Ssi_agentBase
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
+ (instancetype)messagePacker __attribute__((swift_name("init()")));

/**
 @note This method converts instances of CancellationException to errors.
 Other uncaught Kotlin exceptions are fatal.
*/
- (void)packAndPrepareForwardMessageMessage:(Ssi_agentMessage *)message connection:(Ssi_agentConnection *)connection walletConnector:(Ssi_agentWalletConnector *)walletConnector completionHandler:(void (^)(Ssi_agentMessageEnvelop * _Nullable, NSError * _Nullable))completionHandler __attribute__((swift_name("packAndPrepareForwardMessage(message:connection:walletConnector:completionHandler:)")));
@end;

__attribute__((swift_name("MessageProcessor")))
@protocol Ssi_agentMessageProcessor
@required

/**
 @note This method converts instances of CancellationException to errors.
 Other uncaught Kotlin exceptions are fatal.
*/
- (void)processMessageMessageContext:(Ssi_agentMessageContext *)messageContext completionHandler:(void (^)(Ssi_agentKotlinUnit * _Nullable, NSError * _Nullable))completionHandler __attribute__((swift_name("processMessage(messageContext:completionHandler:)")));
@end;

__attribute__((swift_name("AbstractMessageProcessor")))
@interface Ssi_agentAbstractMessageProcessor : Ssi_agentBase <Ssi_agentMessageProcessor>
- (instancetype)initWithWalletConnector:(Ssi_agentWalletConnector *)walletConnector ledgerConnector:(id<Ssi_agentLedgerConnector>)ledgerConnector transport:(id<Ssi_agentTransport>)transport callbacks:(Ssi_agentCallbacks *)callbacks trustPingProcessor:(id<Ssi_agentTrustPingProcessor> _Nullable)trustPingProcessor trustPingTrackerService:(Ssi_agentTrustPingTrackerService * _Nullable)trustPingTrackerService __attribute__((swift_name("init(walletConnector:ledgerConnector:transport:callbacks:trustPingProcessor:trustPingTrackerService:)"))) __attribute__((objc_designated_initializer));
- (id<Ssi_agentMessageType>)getMessageTypeMessage:(NSString *)message __attribute__((swift_name("getMessageType(message:)")));
- (Ssi_agentKotlinEnum *)getMessageTypeGenericMessage:(NSString *)message __attribute__((swift_name("getMessageTypeGeneric(message:)")));

/**
 @note This method converts instances of CancellationException to errors.
 Other uncaught Kotlin exceptions are fatal.
*/
- (void)processMessageMessageContext:(Ssi_agentMessageContext *)messageContext completionHandler:(void (^)(Ssi_agentKotlinUnit * _Nullable, NSError * _Nullable))completionHandler __attribute__((swift_name("processMessage(messageContext:completionHandler:)")));
@property (readonly) Ssi_agentCallbacks *callbacks __attribute__((swift_name("callbacks")));
@property (readonly) id<Ssi_agentLedgerConnector> ledgerConnector __attribute__((swift_name("ledgerConnector")));
@property (readonly) id<Ssi_agentTransport> transport __attribute__((swift_name("transport")));
@property (readonly) id<Ssi_agentTrustPingProcessor> _Nullable trustPingProcessor __attribute__((swift_name("trustPingProcessor")));
@property (readonly) Ssi_agentTrustPingTrackerService * _Nullable trustPingTrackerService __attribute__((swift_name("trustPingTrackerService")));
@property (readonly) Ssi_agentWalletConnector *walletConnector __attribute__((swift_name("walletConnector")));
@end;

__attribute__((swift_name("MessageType")))
@protocol Ssi_agentMessageType
@required
- (id<Ssi_agentAction> (^)(Ssi_agentActionParams *))getMessageHandler __attribute__((swift_name("getMessageHandler()")));
- (NSString *)getTypeString __attribute__((swift_name("getTypeString()")));
@end;

__attribute__((swift_name("TrustPingProcessor")))
@protocol Ssi_agentTrustPingProcessor
@required

/**
 @note This method converts instances of CancellationException to errors.
 Other uncaught Kotlin exceptions are fatal.
*/
- (void)processMessageMessageContext:(Ssi_agentMessageContext *)messageContext completionHandler:(void (^)(Ssi_agentKotlinUnit * _Nullable, NSError * _Nullable))completionHandler __attribute__((swift_name("processMessage(messageContext:completionHandler:)")));

/**
 @note This method converts instances of CancellationException to errors.
 Other uncaught Kotlin exceptions are fatal.
*/
- (void)sendTrustPingOverConnectionConnection:(Ssi_agentConnection *)connection completionHandler:(void (^)(Ssi_agentBoolean * _Nullable, NSError * _Nullable))completionHandler __attribute__((swift_name("sendTrustPingOverConnection(connection:completionHandler:)")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("TrustPingProcessorImpl")))
@interface Ssi_agentTrustPingProcessorImpl : Ssi_agentAbstractMessageProcessor <Ssi_agentTrustPingProcessor>
- (instancetype)initWithWalletConnector:(Ssi_agentWalletConnector *)walletConnector ledgerConnector:(id<Ssi_agentLedgerConnector>)ledgerConnector transport:(id<Ssi_agentTransport>)transport callbacks:(Ssi_agentCallbacks *)callbacks trustPingProcessor:(id<Ssi_agentTrustPingProcessor> _Nullable)trustPingProcessor trustPingTrackerService:(Ssi_agentTrustPingTrackerService *)trustPingTrackerService __attribute__((swift_name("init(walletConnector:ledgerConnector:transport:callbacks:trustPingProcessor:trustPingTrackerService:)"))) __attribute__((objc_designated_initializer));
- (id<Ssi_agentMessageType>)getMessageTypeMessage:(NSString *)message __attribute__((swift_name("getMessageType(message:)")));

/**
 @note This method converts instances of CancellationException to errors.
 Other uncaught Kotlin exceptions are fatal.
*/
- (void)sendTrustPingOverConnectionConnection:(Ssi_agentConnection *)connection completionHandler:(void (^)(Ssi_agentBoolean * _Nullable, NSError * _Nullable))completionHandler __attribute__((swift_name("sendTrustPingOverConnection(connection:completionHandler:)")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("TrustPingProcessorImpl.TrustPingMessageType")))
@interface Ssi_agentTrustPingProcessorImplTrustPingMessageType : Ssi_agentKotlinEnum<Ssi_agentTrustPingProcessorImplTrustPingMessageType *> <Ssi_agentMessageType>
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
- (instancetype)initWithName:(NSString *)name ordinal:(int32_t)ordinal __attribute__((swift_name("init(name:ordinal:)"))) __attribute__((objc_designated_initializer)) __attribute__((unavailable));
@property (class, readonly) Ssi_agentTrustPingProcessorImplTrustPingMessageType *trustPingResponse __attribute__((swift_name("trustPingResponse")));
@property (class, readonly) Ssi_agentTrustPingProcessorImplTrustPingMessageType *trustPing __attribute__((swift_name("trustPing")));
+ (Ssi_agentKotlinArray<Ssi_agentTrustPingProcessorImplTrustPingMessageType *> *)values __attribute__((swift_name("values()")));
- (id<Ssi_agentAction> (^)(Ssi_agentActionParams *))getMessageHandler __attribute__((swift_name("getMessageHandler()")));
- (NSString *)getTypeString __attribute__((swift_name("getTypeString()")));
@property (readonly) id<Ssi_agentAction> (^_action)(Ssi_agentActionParams *) __attribute__((swift_name("_action")));
@property (readonly) NSString *_typeString __attribute__((swift_name("_typeString")));
@end;

__attribute__((swift_name("CredVerifierProcessor")))
@protocol Ssi_agentCredVerifierProcessor <Ssi_agentMessageProcessor>
@required
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("CredVerifierProcessorImpl")))
@interface Ssi_agentCredVerifierProcessorImpl : Ssi_agentAbstractMessageProcessor <Ssi_agentCredVerifierProcessor>
- (instancetype)initWithWalletConnector:(Ssi_agentWalletConnector *)walletConnector ledgerConnector:(id<Ssi_agentLedgerConnector>)ledgerConnector transport:(id<Ssi_agentTransport>)transport callbacks:(Ssi_agentCallbacks *)callbacks trustPingProcessor:(id<Ssi_agentTrustPingProcessor>)trustPingProcessor trustPingTrackerService:(Ssi_agentTrustPingTrackerService *)trustPingTrackerService __attribute__((swift_name("init(walletConnector:ledgerConnector:transport:callbacks:trustPingProcessor:trustPingTrackerService:)"))) __attribute__((objc_designated_initializer));
- (id<Ssi_agentMessageType>)getMessageTypeMessage:(NSString *)message __attribute__((swift_name("getMessageType(message:)")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("CredVerifierProcessorImpl.CredVerifyMessageType")))
@interface Ssi_agentCredVerifierProcessorImplCredVerifyMessageType : Ssi_agentKotlinEnum<Ssi_agentCredVerifierProcessorImplCredVerifyMessageType *> <Ssi_agentMessageType>
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
- (instancetype)initWithName:(NSString *)name ordinal:(int32_t)ordinal __attribute__((swift_name("init(name:ordinal:)"))) __attribute__((objc_designated_initializer)) __attribute__((unavailable));
@property (class, readonly) Ssi_agentCredVerifierProcessorImplCredVerifyMessageType *presentationRequest __attribute__((swift_name("presentationRequest")));
@property (class, readonly) Ssi_agentCredVerifierProcessorImplCredVerifyMessageType *presentation __attribute__((swift_name("presentation")));
@property (class, readonly) Ssi_agentCredVerifierProcessorImplCredVerifyMessageType *presentationProposal __attribute__((swift_name("presentationProposal")));
@property (class, readonly) Ssi_agentCredVerifierProcessorImplCredVerifyMessageType *problemReport __attribute__((swift_name("problemReport")));
@property (class, readonly) Ssi_agentCredVerifierProcessorImplCredVerifyMessageType *presentationAck __attribute__((swift_name("presentationAck")));
+ (Ssi_agentKotlinArray<Ssi_agentCredVerifierProcessorImplCredVerifyMessageType *> *)values __attribute__((swift_name("values()")));
- (id<Ssi_agentAction> (^)(Ssi_agentActionParams *))getMessageHandler __attribute__((swift_name("getMessageHandler()")));
- (NSString *)getTypeString __attribute__((swift_name("getTypeString()")));
@property (readonly) id<Ssi_agentAction> (^_action)(Ssi_agentActionParams *) __attribute__((swift_name("_action")));
@property (readonly) NSString *_typeString __attribute__((swift_name("_typeString")));
@end;

__attribute__((swift_name("CredIssuerProcessor")))
@protocol Ssi_agentCredIssuerProcessor <Ssi_agentMessageProcessor>
@required
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("CredIssuerProcessorImpl")))
@interface Ssi_agentCredIssuerProcessorImpl : Ssi_agentAbstractMessageProcessor <Ssi_agentCredIssuerProcessor>
- (instancetype)initWithWalletConnector:(Ssi_agentWalletConnector *)walletConnector ledgerConnector:(id<Ssi_agentLedgerConnector>)ledgerConnector transport:(id<Ssi_agentTransport>)transport callbacks:(Ssi_agentCallbacks *)callbacks trustPingProcessor:(id<Ssi_agentTrustPingProcessor>)trustPingProcessor trustPingTrackerService:(Ssi_agentTrustPingTrackerService *)trustPingTrackerService __attribute__((swift_name("init(walletConnector:ledgerConnector:transport:callbacks:trustPingProcessor:trustPingTrackerService:)"))) __attribute__((objc_designated_initializer));
- (id<Ssi_agentMessageType>)getMessageTypeMessage:(NSString *)message __attribute__((swift_name("getMessageType(message:)")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("CredIssuerProcessorImpl.CredIssueMessageType")))
@interface Ssi_agentCredIssuerProcessorImplCredIssueMessageType : Ssi_agentKotlinEnum<Ssi_agentCredIssuerProcessorImplCredIssueMessageType *> <Ssi_agentMessageType>
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
- (instancetype)initWithName:(NSString *)name ordinal:(int32_t)ordinal __attribute__((swift_name("init(name:ordinal:)"))) __attribute__((objc_designated_initializer)) __attribute__((unavailable));
@property (class, readonly) Ssi_agentCredIssuerProcessorImplCredIssueMessageType *credentialProposal __attribute__((swift_name("credentialProposal")));
@property (class, readonly) Ssi_agentCredIssuerProcessorImplCredIssueMessageType *credentialOffer __attribute__((swift_name("credentialOffer")));
@property (class, readonly) Ssi_agentCredIssuerProcessorImplCredIssueMessageType *credentialRequest __attribute__((swift_name("credentialRequest")));
@property (class, readonly) Ssi_agentCredIssuerProcessorImplCredIssueMessageType *credential __attribute__((swift_name("credential")));
@property (class, readonly) Ssi_agentCredIssuerProcessorImplCredIssueMessageType *credentialAck __attribute__((swift_name("credentialAck")));
@property (class, readonly) Ssi_agentCredIssuerProcessorImplCredIssueMessageType *credentialReject __attribute__((swift_name("credentialReject")));
+ (Ssi_agentKotlinArray<Ssi_agentCredIssuerProcessorImplCredIssueMessageType *> *)values __attribute__((swift_name("values()")));
- (id<Ssi_agentAction> (^)(Ssi_agentActionParams *))getMessageHandler __attribute__((swift_name("getMessageHandler()")));
- (NSString *)getTypeString __attribute__((swift_name("getTypeString()")));
@property (readonly) id<Ssi_agentAction> (^_action)(Ssi_agentActionParams *) __attribute__((swift_name("_action")));
@property (readonly) NSString *_typeString __attribute__((swift_name("_typeString")));
@end;

__attribute__((swift_name("DidExchangeProcessor")))
@protocol Ssi_agentDidExchangeProcessor
@required

/**
 @note This method converts instances of CancellationException to errors.
 Other uncaught Kotlin exceptions are fatal.
*/
- (void)initiateConnectionByInvitationInvitation:(NSString *)invitation completionHandler:(void (^)(Ssi_agentConnection * _Nullable, NSError * _Nullable))completionHandler __attribute__((swift_name("initiateConnectionByInvitation(invitation:completionHandler:)")));

/**
 @note This method converts instances of CancellationException to errors.
 Other uncaught Kotlin exceptions are fatal.
*/
- (void)processMessageMessageContext:(Ssi_agentMessageContext *)messageContext completionHandler:(void (^)(Ssi_agentKotlinUnit * _Nullable, NSError * _Nullable))completionHandler __attribute__((swift_name("processMessage(messageContext:completionHandler:)")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("DidExchangeProcessorImpl")))
@interface Ssi_agentDidExchangeProcessorImpl : Ssi_agentAbstractMessageProcessor <Ssi_agentDidExchangeProcessor>
- (instancetype)initWithWalletConnector:(Ssi_agentWalletConnector *)walletConnector ledgerConnector:(id<Ssi_agentLedgerConnector>)ledgerConnector transport:(id<Ssi_agentTransport>)transport callbacks:(Ssi_agentCallbacks *)callbacks trustPingProcessor:(id<Ssi_agentTrustPingProcessor>)trustPingProcessor trustPingTrackerService:(Ssi_agentTrustPingTrackerService *)trustPingTrackerService __attribute__((swift_name("init(walletConnector:ledgerConnector:transport:callbacks:trustPingProcessor:trustPingTrackerService:)"))) __attribute__((objc_designated_initializer));
- (id<Ssi_agentMessageType>)getMessageTypeMessage:(NSString *)message __attribute__((swift_name("getMessageType(message:)")));

/**
 @note This method converts instances of CancellationException to errors.
 Other uncaught Kotlin exceptions are fatal.
*/
- (void)initiateConnectionByInvitationInvitation:(NSString *)invitation completionHandler:(void (^)(Ssi_agentConnection * _Nullable, NSError * _Nullable))completionHandler __attribute__((swift_name("initiateConnectionByInvitation(invitation:completionHandler:)")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("DidExchangeProcessorImpl.DidExchangeMessageType")))
@interface Ssi_agentDidExchangeProcessorImplDidExchangeMessageType : Ssi_agentKotlinEnum<Ssi_agentDidExchangeProcessorImplDidExchangeMessageType *> <Ssi_agentMessageType>
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
- (instancetype)initWithName:(NSString *)name ordinal:(int32_t)ordinal __attribute__((swift_name("init(name:ordinal:)"))) __attribute__((objc_designated_initializer)) __attribute__((unavailable));
@property (class, readonly) Ssi_agentDidExchangeProcessorImplDidExchangeMessageType *invitation __attribute__((swift_name("invitation")));
@property (class, readonly) Ssi_agentDidExchangeProcessorImplDidExchangeMessageType *connectionRequest __attribute__((swift_name("connectionRequest")));
@property (class, readonly) Ssi_agentDidExchangeProcessorImplDidExchangeMessageType *connectionResponse __attribute__((swift_name("connectionResponse")));
+ (Ssi_agentKotlinArray<Ssi_agentDidExchangeProcessorImplDidExchangeMessageType *> *)values __attribute__((swift_name("values()")));
- (id<Ssi_agentAction> (^)(Ssi_agentActionParams *))getMessageHandler __attribute__((swift_name("getMessageHandler()")));
- (NSString *)getTypeString __attribute__((swift_name("getTypeString()")));
@property (readonly) id<Ssi_agentAction> (^_action)(Ssi_agentActionParams *) __attribute__((swift_name("_action")));
@property (readonly) NSString *_typeString __attribute__((swift_name("_typeString")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("Connection")))
@interface Ssi_agentConnection : Ssi_agentBase
- (instancetype)initWithId:(NSString *)id state:(NSString *)state invitation:(NSString *)invitation isSelfInitiated:(BOOL)isSelfInitiated peerRecipientKeys:(NSArray<NSString *> *)peerRecipientKeys peerVerkey:(NSString * _Nullable)peerVerkey peerDid:(NSString * _Nullable)peerDid endpoint:(NSString *)endpoint __attribute__((swift_name("init(id:state:invitation:isSelfInitiated:peerRecipientKeys:peerVerkey:peerDid:endpoint:)"))) __attribute__((objc_designated_initializer));
- (NSString *)component1 __attribute__((swift_name("component1()")));
- (NSString *)component2 __attribute__((swift_name("component2()")));
- (NSString *)component3 __attribute__((swift_name("component3()")));
- (BOOL)component4 __attribute__((swift_name("component4()")));
- (NSArray<NSString *> *)component5 __attribute__((swift_name("component5()")));
- (NSString * _Nullable)component6 __attribute__((swift_name("component6()")));
- (NSString * _Nullable)component7 __attribute__((swift_name("component7()")));
- (NSString *)component8 __attribute__((swift_name("component8()")));
- (Ssi_agentConnection *)doCopyId:(NSString *)id state:(NSString *)state invitation:(NSString *)invitation isSelfInitiated:(BOOL)isSelfInitiated peerRecipientKeys:(NSArray<NSString *> *)peerRecipientKeys peerVerkey:(NSString * _Nullable)peerVerkey peerDid:(NSString * _Nullable)peerDid endpoint:(NSString *)endpoint __attribute__((swift_name("doCopy(id:state:invitation:isSelfInitiated:peerRecipientKeys:peerVerkey:peerDid:endpoint:)")));
- (BOOL)isEqual:(id _Nullable)other __attribute__((swift_name("isEqual(_:)")));
- (NSUInteger)hash __attribute__((swift_name("hash()")));
- (NSString *)toJson __attribute__((swift_name("toJson()")));
- (NSString *)description __attribute__((swift_name("description()")));
@property (readonly) NSString *endpoint __attribute__((swift_name("endpoint")));
@property (readonly) NSString *id __attribute__((swift_name("id")));
@property (readonly) NSString *invitation __attribute__((swift_name("invitation")));
@property (readonly) BOOL isSelfInitiated __attribute__((swift_name("isSelfInitiated")));
@property (readonly) NSString * _Nullable peerDid __attribute__((swift_name("peerDid")));
@property (readonly) NSArray<NSString *> *peerRecipientKeys __attribute__((swift_name("peerRecipientKeys")));
@property (readonly) NSString * _Nullable peerVerkey __attribute__((swift_name("peerVerkey")));
@property (readonly) NSString *state __attribute__((swift_name("state")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("Connection.Companion")))
@interface Ssi_agentConnectionCompanion : Ssi_agentBase
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
+ (instancetype)companion __attribute__((swift_name("init()")));
- (Ssi_agentConnection *)fromJsonJsonString:(NSString *)jsonString __attribute__((swift_name("fromJson(jsonString:)")));
- (id<Ssi_agentKotlinx_serialization_coreKSerializer>)serializer __attribute__((swift_name("serializer()")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("CredentialExchangeRecord")))
@interface Ssi_agentCredentialExchangeRecord : Ssi_agentBase
- (instancetype)initWithState:(NSString *)state connectionId:(NSString *)connectionId thread:(Ssi_agentThread *)thread credentialOfferContainer:(Ssi_agentCredentialOfferContainer *)credentialOfferContainer credentialRequestContainer:(Ssi_agentCredentialRequestContainer *)credentialRequestContainer credentialRequestInfo:(Ssi_agentCredentialRequestInfo *)credentialRequestInfo credentialDefinition:(id<Ssi_agentCredentialDefinition>)credentialDefinition __attribute__((swift_name("init(state:connectionId:thread:credentialOfferContainer:credentialRequestContainer:credentialRequestInfo:credentialDefinition:)"))) __attribute__((objc_designated_initializer));
- (NSString *)component1 __attribute__((swift_name("component1()")));
- (NSString *)component2 __attribute__((swift_name("component2()")));
- (Ssi_agentThread *)component3 __attribute__((swift_name("component3()")));
- (Ssi_agentCredentialOfferContainer *)component4 __attribute__((swift_name("component4()")));
- (Ssi_agentCredentialRequestContainer *)component5 __attribute__((swift_name("component5()")));
- (Ssi_agentCredentialRequestInfo *)component6 __attribute__((swift_name("component6()")));
- (id<Ssi_agentCredentialDefinition>)component7 __attribute__((swift_name("component7()")));
- (Ssi_agentCredentialExchangeRecord *)doCopyState:(NSString *)state connectionId:(NSString *)connectionId thread:(Ssi_agentThread *)thread credentialOfferContainer:(Ssi_agentCredentialOfferContainer *)credentialOfferContainer credentialRequestContainer:(Ssi_agentCredentialRequestContainer *)credentialRequestContainer credentialRequestInfo:(Ssi_agentCredentialRequestInfo *)credentialRequestInfo credentialDefinition:(id<Ssi_agentCredentialDefinition>)credentialDefinition __attribute__((swift_name("doCopy(state:connectionId:thread:credentialOfferContainer:credentialRequestContainer:credentialRequestInfo:credentialDefinition:)")));
- (BOOL)isEqual:(id _Nullable)other __attribute__((swift_name("isEqual(_:)")));
- (NSUInteger)hash __attribute__((swift_name("hash()")));
- (NSString *)description __attribute__((swift_name("description()")));
@property (readonly) NSString *connectionId __attribute__((swift_name("connectionId")));
@property (readonly) id<Ssi_agentCredentialDefinition> credentialDefinition __attribute__((swift_name("credentialDefinition")));
@property (readonly) Ssi_agentCredentialOfferContainer *credentialOfferContainer __attribute__((swift_name("credentialOfferContainer")));
@property (readonly) Ssi_agentCredentialRequestContainer *credentialRequestContainer __attribute__((swift_name("credentialRequestContainer")));
@property (readonly) Ssi_agentCredentialRequestInfo *credentialRequestInfo __attribute__((swift_name("credentialRequestInfo")));
@property (readonly) NSString *state __attribute__((swift_name("state")));
@property (readonly) Ssi_agentThread *thread __attribute__((swift_name("thread")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("CredentialExchangeRecord.Companion")))
@interface Ssi_agentCredentialExchangeRecordCompanion : Ssi_agentBase
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
+ (instancetype)companion __attribute__((swift_name("init()")));
- (id<Ssi_agentKotlinx_serialization_coreKSerializer>)serializer __attribute__((swift_name("serializer()")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("DidConfig")))
@interface Ssi_agentDidConfig : Ssi_agentBase
- (instancetype)initWithDid:(NSString * _Nullable)did seed:(NSString * _Nullable)seed cryptoType:(NSString * _Nullable)cryptoType cid:(Ssi_agentBoolean * _Nullable)cid __attribute__((swift_name("init(did:seed:cryptoType:cid:)"))) __attribute__((objc_designated_initializer));
- (NSString * _Nullable)component1 __attribute__((swift_name("component1()")));
- (NSString * _Nullable)component2 __attribute__((swift_name("component2()")));
- (NSString * _Nullable)component3 __attribute__((swift_name("component3()")));
- (Ssi_agentBoolean * _Nullable)component4 __attribute__((swift_name("component4()")));
- (Ssi_agentDidConfig *)doCopyDid:(NSString * _Nullable)did seed:(NSString * _Nullable)seed cryptoType:(NSString * _Nullable)cryptoType cid:(Ssi_agentBoolean * _Nullable)cid __attribute__((swift_name("doCopy(did:seed:cryptoType:cid:)")));
- (BOOL)isEqual:(id _Nullable)other __attribute__((swift_name("isEqual(_:)")));
- (NSUInteger)hash __attribute__((swift_name("hash()")));
- (NSString *)description __attribute__((swift_name("description()")));
@property (readonly) Ssi_agentBoolean * _Nullable cid __attribute__((swift_name("cid")));
@property (readonly) NSString * _Nullable cryptoType __attribute__((swift_name("cryptoType")));
@property (readonly) NSString * _Nullable did __attribute__((swift_name("did")));
@property (readonly) NSString * _Nullable seed __attribute__((swift_name("seed")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("DidConfig.Companion")))
@interface Ssi_agentDidConfigCompanion : Ssi_agentBase
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
+ (instancetype)companion __attribute__((swift_name("init()")));
- (id<Ssi_agentKotlinx_serialization_coreKSerializer>)serializer __attribute__((swift_name("serializer()")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("IdentityDetails")))
@interface Ssi_agentIdentityDetails : Ssi_agentBase
- (instancetype)initWithDid:(NSString *)did verkey:(NSString *)verkey alias:(NSString * _Nullable)alias role:(NSString * _Nullable)role __attribute__((swift_name("init(did:verkey:alias:role:)"))) __attribute__((objc_designated_initializer));
- (NSString *)component1 __attribute__((swift_name("component1()")));
- (NSString *)component2 __attribute__((swift_name("component2()")));
- (NSString * _Nullable)component3 __attribute__((swift_name("component3()")));
- (NSString * _Nullable)component4 __attribute__((swift_name("component4()")));
- (Ssi_agentIdentityDetails *)doCopyDid:(NSString *)did verkey:(NSString *)verkey alias:(NSString * _Nullable)alias role:(NSString * _Nullable)role __attribute__((swift_name("doCopy(did:verkey:alias:role:)")));
- (BOOL)isEqual:(id _Nullable)other __attribute__((swift_name("isEqual(_:)")));
- (NSUInteger)hash __attribute__((swift_name("hash()")));
- (NSString *)description __attribute__((swift_name("description()")));
@property (readonly) NSString * _Nullable alias __attribute__((swift_name("alias")));
@property (readonly) NSString *did __attribute__((swift_name("did")));
@property (readonly) NSString * _Nullable role __attribute__((swift_name("role")));
@property (readonly) NSString *verkey __attribute__((swift_name("verkey")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("BasicMessageWithTypeOnly")))
@interface Ssi_agentBasicMessageWithTypeOnly : Ssi_agentBase
- (instancetype)initWithType:(NSString *)type __attribute__((swift_name("init(type:)"))) __attribute__((objc_designated_initializer));
- (NSString *)component1 __attribute__((swift_name("component1()")));
- (Ssi_agentBasicMessageWithTypeOnly *)doCopyType:(NSString *)type __attribute__((swift_name("doCopy(type:)")));
- (BOOL)isEqual:(id _Nullable)other __attribute__((swift_name("isEqual(_:)")));
- (NSUInteger)hash __attribute__((swift_name("hash()")));
- (NSString *)description __attribute__((swift_name("description()")));
@property (readonly) NSString *type __attribute__((swift_name("type")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("BasicMessageWithTypeOnly.Companion")))
@interface Ssi_agentBasicMessageWithTypeOnlyCompanion : Ssi_agentBase
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
+ (instancetype)companion __attribute__((swift_name("init()")));
- (id<Ssi_agentKotlinx_serialization_coreKSerializer>)serializer __attribute__((swift_name("serializer()")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("Message")))
@interface Ssi_agentMessage : Ssi_agentBase
- (instancetype)initWithPayload:(NSString *)payload __attribute__((swift_name("init(payload:)"))) __attribute__((objc_designated_initializer));
- (NSString *)component1 __attribute__((swift_name("component1()")));
- (Ssi_agentMessage *)doCopyPayload:(NSString *)payload __attribute__((swift_name("doCopy(payload:)")));
- (BOOL)isEqual:(id _Nullable)other __attribute__((swift_name("isEqual(_:)")));
- (NSUInteger)hash __attribute__((swift_name("hash()")));
- (NSString *)description __attribute__((swift_name("description()")));
@property (readonly) NSString *payload __attribute__((swift_name("payload")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("MessageContext")))
@interface Ssi_agentMessageContext : Ssi_agentBase
- (instancetype)initWithConnection:(Ssi_agentConnection * _Nullable)connection receivedUnpackedMessage:(Ssi_agentReceivedUnpackedMessage *)receivedUnpackedMessage __attribute__((swift_name("init(connection:receivedUnpackedMessage:)"))) __attribute__((objc_designated_initializer));
- (Ssi_agentConnection * _Nullable)component1 __attribute__((swift_name("component1()")));
- (Ssi_agentReceivedUnpackedMessage *)component2 __attribute__((swift_name("component2()")));
- (Ssi_agentMessageContext *)doCopyConnection:(Ssi_agentConnection * _Nullable)connection receivedUnpackedMessage:(Ssi_agentReceivedUnpackedMessage *)receivedUnpackedMessage __attribute__((swift_name("doCopy(connection:receivedUnpackedMessage:)")));
- (BOOL)isEqual:(id _Nullable)other __attribute__((swift_name("isEqual(_:)")));
- (NSUInteger)hash __attribute__((swift_name("hash()")));
- (NSString *)description __attribute__((swift_name("description()")));
@property (readonly) Ssi_agentConnection * _Nullable connection __attribute__((swift_name("connection")));
@property (readonly) Ssi_agentReceivedUnpackedMessage *receivedUnpackedMessage __attribute__((swift_name("receivedUnpackedMessage")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("MessageEnvelop")))
@interface Ssi_agentMessageEnvelop : Ssi_agentBase
- (instancetype)initWithPayload:(NSString *)payload __attribute__((swift_name("init(payload:)"))) __attribute__((objc_designated_initializer));
- (NSString *)component1 __attribute__((swift_name("component1()")));
- (Ssi_agentMessageEnvelop *)doCopyPayload:(NSString *)payload __attribute__((swift_name("doCopy(payload:)")));
- (BOOL)isEqual:(id _Nullable)other __attribute__((swift_name("isEqual(_:)")));
- (NSUInteger)hash __attribute__((swift_name("hash()")));
- (NSString *)description __attribute__((swift_name("description()")));
@property (readonly) NSString *payload __attribute__((swift_name("payload")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("ReceivedUnpackedMessage")))
@interface Ssi_agentReceivedUnpackedMessage : Ssi_agentBase
- (instancetype)initWithMessage:(NSString *)message recipientVerKey:(NSString *)recipientVerKey senderVerKey:(NSString *)senderVerKey __attribute__((swift_name("init(message:recipientVerKey:senderVerKey:)"))) __attribute__((objc_designated_initializer));
- (NSString *)component1 __attribute__((swift_name("component1()")));
- (NSString *)component2 __attribute__((swift_name("component2()")));
- (NSString *)component3 __attribute__((swift_name("component3()")));
- (Ssi_agentReceivedUnpackedMessage *)doCopyMessage:(NSString *)message recipientVerKey:(NSString *)recipientVerKey senderVerKey:(NSString *)senderVerKey __attribute__((swift_name("doCopy(message:recipientVerKey:senderVerKey:)")));
- (BOOL)isEqual:(id _Nullable)other __attribute__((swift_name("isEqual(_:)")));
- (NSUInteger)hash __attribute__((swift_name("hash()")));
- (NSString *)description __attribute__((swift_name("description()")));
@property (readonly) NSString *message __attribute__((swift_name("message")));
@property (readonly) NSString *recipientVerKey __attribute__((swift_name("recipientVerKey")));
@property (readonly) NSString *senderVerKey __attribute__((swift_name("senderVerKey")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("ReceivedUnpackedMessage.Companion")))
@interface Ssi_agentReceivedUnpackedMessageCompanion : Ssi_agentBase
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
+ (instancetype)companion __attribute__((swift_name("init()")));
- (id<Ssi_agentKotlinx_serialization_coreKSerializer>)serializer __attribute__((swift_name("serializer()")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("TransportableMessage")))
@interface Ssi_agentTransportableMessage : Ssi_agentBase
- (instancetype)initWithPayload:(NSString *)payload packed:(BOOL)packed __attribute__((swift_name("init(payload:packed:)"))) __attribute__((objc_designated_initializer));
- (NSString *)component1 __attribute__((swift_name("component1()")));
- (BOOL)component2 __attribute__((swift_name("component2()")));
- (Ssi_agentTransportableMessage *)doCopyPayload:(NSString *)payload packed:(BOOL)packed __attribute__((swift_name("doCopy(payload:packed:)")));
- (BOOL)isEqual:(id _Nullable)other __attribute__((swift_name("isEqual(_:)")));
- (NSUInteger)hash __attribute__((swift_name("hash()")));
- (NSString *)description __attribute__((swift_name("description()")));
@property (readonly) BOOL packed __attribute__((swift_name("packed")));
@property (readonly) NSString *payload __attribute__((swift_name("payload")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("TransportableMessage.Companion")))
@interface Ssi_agentTransportableMessageCompanion : Ssi_agentBase
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
+ (instancetype)companion __attribute__((swift_name("init()")));
- (id<Ssi_agentKotlinx_serialization_coreKSerializer>)serializer __attribute__((swift_name("serializer()")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("AppSocket")))
@interface Ssi_agentAppSocket : Ssi_agentBase
- (instancetype)initWithUrl:(NSString *)url incomingMessagesQueue:(NSMutableArray<Ssi_agentMessageEnvelop *> *)incomingMessagesQueue __attribute__((swift_name("init(url:incomingMessagesQueue:)"))) __attribute__((objc_designated_initializer));

/**
 @note This method converts instances of CancellationException to errors.
 Other uncaught Kotlin exceptions are fatal.
*/
- (void)connectWithCompletionHandler:(void (^)(Ssi_agentKotlinUnit * _Nullable, NSError * _Nullable))completionHandler __attribute__((swift_name("connect(completionHandler:)")));
- (void)disconnect __attribute__((swift_name("disconnect()")));
- (void)sendMsg:(NSString *)msg __attribute__((swift_name("send(msg:)")));
@property (readonly) Ssi_agentAppSocketState *currentState __attribute__((swift_name("currentState")));
@property (readonly) Ssi_agentKotlinThrowable * _Nullable socketError __attribute__((swift_name("socketError")));
@property void (^ _Nullable stateListener)(Ssi_agentAppSocketState *) __attribute__((swift_name("stateListener")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("AppSocket.State")))
@interface Ssi_agentAppSocketState : Ssi_agentKotlinEnum<Ssi_agentAppSocketState *>
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
- (instancetype)initWithName:(NSString *)name ordinal:(int32_t)ordinal __attribute__((swift_name("init(name:ordinal:)"))) __attribute__((objc_designated_initializer)) __attribute__((unavailable));
@property (class, readonly) Ssi_agentAppSocketState *connecting __attribute__((swift_name("connecting")));
@property (class, readonly) Ssi_agentAppSocketState *connected __attribute__((swift_name("connected")));
@property (class, readonly) Ssi_agentAppSocketState *closing __attribute__((swift_name("closing")));
@property (class, readonly) Ssi_agentAppSocketState *closed __attribute__((swift_name("closed")));
+ (Ssi_agentKotlinArray<Ssi_agentAppSocketState *> *)values __attribute__((swift_name("values()")));
@end;

__attribute__((swift_name("PlatformSocketListener")))
@protocol Ssi_agentPlatformSocketListener
@required
- (void)onClosedCode:(int32_t)code reason:(NSString *)reason __attribute__((swift_name("onClosed(code:reason:)")));
- (void)onClosingCode:(int32_t)code reason:(NSString *)reason __attribute__((swift_name("onClosing(code:reason:)")));
- (void)onFailureT:(Ssi_agentKotlinThrowable *)t __attribute__((swift_name("onFailure(t:)")));
- (void)onMessageMsg:(NSString *)msg __attribute__((swift_name("onMessage(msg:)")));
- (void)onOpen __attribute__((swift_name("onOpen()")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("SocketClosureDetails")))
@interface Ssi_agentSocketClosureDetails : Ssi_agentBase
- (instancetype)initWithCode:(int32_t)code reason:(NSString *)reason __attribute__((swift_name("init(code:reason:)"))) __attribute__((objc_designated_initializer));
- (int32_t)component1 __attribute__((swift_name("component1()")));
- (NSString *)component2 __attribute__((swift_name("component2()")));
- (Ssi_agentSocketClosureDetails *)doCopyCode:(int32_t)code reason:(NSString *)reason __attribute__((swift_name("doCopy(code:reason:)")));
- (BOOL)isEqual:(id _Nullable)other __attribute__((swift_name("isEqual(_:)")));
- (NSUInteger)hash __attribute__((swift_name("hash()")));
- (NSString *)description __attribute__((swift_name("description()")));
@property (readonly) int32_t code __attribute__((swift_name("code")));
@property (readonly) NSString *reason __attribute__((swift_name("reason")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("SocketListenerAdapter")))
@interface Ssi_agentSocketListenerAdapter : Ssi_agentBase
- (instancetype)initWithSocketOpenedChannel:(id<Ssi_agentKotlinx_coroutines_coreChannel>)socketOpenedChannel socketReceivedMessageChannel:(id<Ssi_agentKotlinx_coroutines_coreChannel>)socketReceivedMessageChannel socketFailureChannel:(id<Ssi_agentKotlinx_coroutines_coreChannel>)socketFailureChannel socketClosedChannel:(id<Ssi_agentKotlinx_coroutines_coreChannel>)socketClosedChannel __attribute__((swift_name("init(socketOpenedChannel:socketReceivedMessageChannel:socketFailureChannel:socketClosedChannel:)"))) __attribute__((objc_designated_initializer));
- (void)onClosedSocketClosureDetails:(Ssi_agentSocketClosureDetails *)socketClosureDetails __attribute__((swift_name("onClosed(socketClosureDetails:)")));
- (void)onFailureThrowable:(Ssi_agentKotlinThrowable *)throwable __attribute__((swift_name("onFailure(throwable:)")));
- (void)onMessageReceivedMessage:(NSString *)message __attribute__((swift_name("onMessageReceived(message:)")));
- (void)onOpened __attribute__((swift_name("onOpened()")));
@property (readonly) id<Ssi_agentKotlinx_coroutines_coreChannel> socketClosedChannel __attribute__((swift_name("socketClosedChannel")));
@property (readonly) id<Ssi_agentKotlinx_coroutines_coreChannel> socketFailureChannel __attribute__((swift_name("socketFailureChannel")));
@property (readonly) id<Ssi_agentKotlinx_coroutines_coreChannel> socketOpenedChannel __attribute__((swift_name("socketOpenedChannel")));
@property (readonly) id<Ssi_agentKotlinx_coroutines_coreChannel> socketReceivedMessageChannel __attribute__((swift_name("socketReceivedMessageChannel")));
@end;

__attribute__((swift_name("Transport")))
@protocol Ssi_agentTransport
@required

/**
 @note This method converts instances of CancellationException to errors.
 Other uncaught Kotlin exceptions are fatal.
*/
- (void)receiveNextMessageWithCompletionHandler:(void (^)(Ssi_agentMessageEnvelop * _Nullable, NSError * _Nullable))completionHandler __attribute__((swift_name("receiveNextMessage(completionHandler:)")));

/**
 @note This method converts instances of CancellationException to errors.
 Other uncaught Kotlin exceptions are fatal.
*/
- (void)sendMessageConnection:(Ssi_agentConnection *)connection message:(Ssi_agentMessageEnvelop *)message completionHandler:(void (^)(Ssi_agentKotlinUnit * _Nullable, NSError * _Nullable))completionHandler __attribute__((swift_name("sendMessage(connection:message:completionHandler:)")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("WebSocketTransportImpl")))
@interface Ssi_agentWebSocketTransportImpl : Ssi_agentBase <Ssi_agentTransport>
- (instancetype)init __attribute__((swift_name("init()"))) __attribute__((objc_designated_initializer));
+ (instancetype)new __attribute__((availability(swift, unavailable, message="use object initializers instead")));
- (void)doInit __attribute__((swift_name("doInit()")));

/**
 @note This method converts instances of CancellationException to errors.
 Other uncaught Kotlin exceptions are fatal.
*/
- (void)receiveNextMessageWithCompletionHandler:(void (^)(Ssi_agentMessageEnvelop * _Nullable, NSError * _Nullable))completionHandler __attribute__((swift_name("receiveNextMessage(completionHandler:)")));

/**
 @note This method converts instances of CancellationException to errors.
 Other uncaught Kotlin exceptions are fatal.
*/
- (void)sendMessageConnection:(Ssi_agentConnection *)connection message:(Ssi_agentMessageEnvelop *)message completionHandler:(void (^)(Ssi_agentKotlinUnit * _Nullable, NSError * _Nullable))completionHandler __attribute__((swift_name("sendMessage(connection:message:completionHandler:)")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("WebSocketWrapper")))
@interface Ssi_agentWebSocketWrapper : Ssi_agentBase
- (instancetype)initWithWebsocket:(NSURLSessionWebSocketTask * _Nullable)websocket __attribute__((swift_name("init(websocket:)"))) __attribute__((objc_designated_initializer));
- (NSURLSessionWebSocketTask * _Nullable)component1 __attribute__((swift_name("component1()")));
- (Ssi_agentWebSocketWrapper *)doCopyWebsocket:(NSURLSessionWebSocketTask * _Nullable)websocket __attribute__((swift_name("doCopy(websocket:)")));
- (BOOL)isEqual:(id _Nullable)other __attribute__((swift_name("isEqual(_:)")));
- (NSUInteger)hash __attribute__((swift_name("hash()")));
- (NSString *)description __attribute__((swift_name("description()")));
@property NSURLSessionWebSocketTask * _Nullable websocket __attribute__((swift_name("websocket")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("Callbacks")))
@interface Ssi_agentCallbacks : Ssi_agentBase
- (instancetype)initWithConnectionInitiatorController:(id<Ssi_agentConnectionInitiatorController> _Nullable)connectionInitiatorController connectionResponderController:(id<Ssi_agentConnectionResponderController> _Nullable)connectionResponderController credReceiverController:(id<Ssi_agentCredReceiverController> _Nullable)credReceiverController credIssuerController:(id<Ssi_agentCredIssuerController> _Nullable)credIssuerController credPresenterController:(id<Ssi_agentCredPresenterController> _Nullable)credPresenterController credVerifierController:(id<Ssi_agentCredVerifierController> _Nullable)credVerifierController __attribute__((swift_name("init(connectionInitiatorController:connectionResponderController:credReceiverController:credIssuerController:credPresenterController:credVerifierController:)"))) __attribute__((objc_designated_initializer));
- (id<Ssi_agentConnectionInitiatorController> _Nullable)component1 __attribute__((swift_name("component1()")));
- (id<Ssi_agentConnectionResponderController> _Nullable)component2 __attribute__((swift_name("component2()")));
- (id<Ssi_agentCredReceiverController> _Nullable)component3 __attribute__((swift_name("component3()")));
- (id<Ssi_agentCredIssuerController> _Nullable)component4 __attribute__((swift_name("component4()")));
- (id<Ssi_agentCredPresenterController> _Nullable)component5 __attribute__((swift_name("component5()")));
- (id<Ssi_agentCredVerifierController> _Nullable)component6 __attribute__((swift_name("component6()")));
- (Ssi_agentCallbacks *)doCopyConnectionInitiatorController:(id<Ssi_agentConnectionInitiatorController> _Nullable)connectionInitiatorController connectionResponderController:(id<Ssi_agentConnectionResponderController> _Nullable)connectionResponderController credReceiverController:(id<Ssi_agentCredReceiverController> _Nullable)credReceiverController credIssuerController:(id<Ssi_agentCredIssuerController> _Nullable)credIssuerController credPresenterController:(id<Ssi_agentCredPresenterController> _Nullable)credPresenterController credVerifierController:(id<Ssi_agentCredVerifierController> _Nullable)credVerifierController __attribute__((swift_name("doCopy(connectionInitiatorController:connectionResponderController:credReceiverController:credIssuerController:credPresenterController:credVerifierController:)")));
- (BOOL)isEqual:(id _Nullable)other __attribute__((swift_name("isEqual(_:)")));
- (NSUInteger)hash __attribute__((swift_name("hash()")));
- (NSString *)description __attribute__((swift_name("description()")));
@property (readonly) id<Ssi_agentConnectionInitiatorController> _Nullable connectionInitiatorController __attribute__((swift_name("connectionInitiatorController")));
@property (readonly) id<Ssi_agentConnectionResponderController> _Nullable connectionResponderController __attribute__((swift_name("connectionResponderController")));
@property (readonly) id<Ssi_agentCredIssuerController> _Nullable credIssuerController __attribute__((swift_name("credIssuerController")));
@property (readonly) id<Ssi_agentCredPresenterController> _Nullable credPresenterController __attribute__((swift_name("credPresenterController")));
@property (readonly) id<Ssi_agentCredReceiverController> _Nullable credReceiverController __attribute__((swift_name("credReceiverController")));
@property (readonly) id<Ssi_agentCredVerifierController> _Nullable credVerifierController __attribute__((swift_name("credVerifierController")));
@end;

__attribute__((swift_name("Environment")))
@protocol Ssi_agentEnvironment
@required
- (NSString *)getWritableFolderInUserHome __attribute__((swift_name("getWritableFolderInUserHome()")));
@end;

__attribute__((swift_name("SsiAgentApi")))
@protocol Ssi_agentSsiAgentApi
@required
- (Ssi_agentConnection *)connectUrl:(NSString *)url __attribute__((swift_name("connect(url:)")));
- (void)disconnectConnection:(Ssi_agentConnection *)connection __attribute__((swift_name("disconnect(connection:)")));
- (void)disconnectConnection:(Ssi_agentConnection *)connection force:(BOOL)force __attribute__((swift_name("disconnect(connection:force:)")));
- (void)disconnectAllForce:(BOOL)force __attribute__((swift_name("disconnectAll(force:)")));
- (NSSet<Ssi_agentConnection *> *)getConnections __attribute__((swift_name("getConnections()")));
- (id<Ssi_agentLedgerConnector>)getLedgerConnector __attribute__((swift_name("getLedgerConnector()")));
- (Ssi_agentWalletConnector *)getWalletConnector __attribute__((swift_name("getWalletConnector()")));
- (void)doInit __attribute__((swift_name("doInit()")));
- (void)issueCredentialOverConnectionConnection:(Ssi_agentConnection *)connection __attribute__((swift_name("issueCredentialOverConnection(connection:)")));
- (void)requestProofOverConnectionConnection:(Ssi_agentConnection *)connection __attribute__((swift_name("requestProofOverConnection(connection:)")));
- (BOOL)sendTrustPingConnection:(Ssi_agentConnection *)connection __attribute__((swift_name("sendTrustPing(connection:)")));
- (void)shutdownForce:(BOOL)force __attribute__((swift_name("shutdown(force:)")));
@end;

__attribute__((swift_name("SsiAgentBuilder")))
@protocol Ssi_agentSsiAgentBuilder
@required
- (id<Ssi_agentSsiAgentApi>)build __attribute__((swift_name("build()")));
- (id<Ssi_agentSsiAgentBuilder>)withConnectionInitiatorControllerConnectionInitiatorController:(id<Ssi_agentConnectionInitiatorController>)connectionInitiatorController __attribute__((swift_name("withConnectionInitiatorController(connectionInitiatorController:)")));
- (id<Ssi_agentSsiAgentBuilder>)withConnectionResponderControllerConnectionResponderController:(id<Ssi_agentConnectionResponderController>)connectionResponderController __attribute__((swift_name("withConnectionResponderController(connectionResponderController:)")));
- (id<Ssi_agentSsiAgentBuilder>)withCredIssuerControllerCredIssuerController:(id<Ssi_agentCredIssuerController>)credIssuerController __attribute__((swift_name("withCredIssuerController(credIssuerController:)")));
- (id<Ssi_agentSsiAgentBuilder>)withCredPresenterControllerCredPresenterController:(id<Ssi_agentCredPresenterController>)credPresenterController __attribute__((swift_name("withCredPresenterController(credPresenterController:)")));
- (id<Ssi_agentSsiAgentBuilder>)withCredReceiverControllerCredReceiverController:(id<Ssi_agentCredReceiverController>)credReceiverController __attribute__((swift_name("withCredReceiverController(credReceiverController:)")));
- (id<Ssi_agentSsiAgentBuilder>)withCredVerifierControllerCredVerifierController:(id<Ssi_agentCredVerifierController>)credVerifierController __attribute__((swift_name("withCredVerifierController(credVerifierController:)")));
- (id<Ssi_agentSsiAgentBuilder>)withEnvironmentEnvironment:(id<Ssi_agentEnvironment>)environment __attribute__((swift_name("withEnvironment(environment:)")));
- (id<Ssi_agentSsiAgentBuilder>)withIssuerIssuer:(id<Ssi_agentIssuer>)issuer __attribute__((swift_name("withIssuer(issuer:)")));
- (id<Ssi_agentSsiAgentBuilder>)withLedgerConnectorLedgerConnector:(id<Ssi_agentLedgerConnector>)ledgerConnector __attribute__((swift_name("withLedgerConnector(ledgerConnector:)")));
- (id<Ssi_agentSsiAgentBuilder>)withProverProver:(id<Ssi_agentProver>)prover __attribute__((swift_name("withProver(prover:)")));
- (id<Ssi_agentSsiAgentBuilder>)withTransportTransport:(id<Ssi_agentTransport>)transport __attribute__((swift_name("withTransport(transport:)")));
- (id<Ssi_agentSsiAgentBuilder>)withTrusteeTrustee:(id<Ssi_agentTrustee>)trustee __attribute__((swift_name("withTrustee(trustee:)")));
- (id<Ssi_agentSsiAgentBuilder>)withVerifierVerifier:(id<Ssi_agentVerifier>)verifier __attribute__((swift_name("withVerifier(verifier:)")));
- (id<Ssi_agentSsiAgentBuilder>)withWalletHolderWalletHolder:(id<Ssi_agentWalletHolder>)walletHolder __attribute__((swift_name("withWalletHolder(walletHolder:)")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("CallbackResult")))
@interface Ssi_agentCallbackResult : Ssi_agentBase
- (instancetype)initWithCanProceedFurther:(BOOL)canProceedFurther __attribute__((swift_name("init(canProceedFurther:)"))) __attribute__((objc_designated_initializer));
- (BOOL)component1 __attribute__((swift_name("component1()")));
- (Ssi_agentCallbackResult *)doCopyCanProceedFurther:(BOOL)canProceedFurther __attribute__((swift_name("doCopy(canProceedFurther:)")));
- (BOOL)isEqual:(id _Nullable)other __attribute__((swift_name("isEqual(_:)")));
- (NSUInteger)hash __attribute__((swift_name("hash()")));
- (NSString *)description __attribute__((swift_name("description()")));
@property (readonly) BOOL canProceedFurther __attribute__((swift_name("canProceedFurther")));
@end;

__attribute__((swift_name("CredPresenterController")))
@protocol Ssi_agentCredPresenterController
@required
- (Ssi_agentCallbackResult *)onDoneConnection:(Ssi_agentConnection *)connection __attribute__((swift_name("onDone(connection:)")));
- (Ssi_agentCallbackResult *)onRequestReceivedConnection:(Ssi_agentConnection *)connection presentationRequest:(Ssi_agentPresentationRequestContainer *)presentationRequest __attribute__((swift_name("onRequestReceived(connection:presentationRequest:)")));
@end;

__attribute__((swift_name("CredVerifierController")))
@protocol Ssi_agentCredVerifierController
@required
@end;

__attribute__((swift_name("CredIssuerController")))
@protocol Ssi_agentCredIssuerController
@required
@end;

__attribute__((swift_name("CredReceiverController")))
@protocol Ssi_agentCredReceiverController
@required
- (Ssi_agentCallbackResult *)onCredentialReceivedConnection:(Ssi_agentConnection *)connection credentialContainer:(Ssi_agentCredentialContainer *)credentialContainer __attribute__((swift_name("onCredentialReceived(connection:credentialContainer:)")));
- (Ssi_agentCallbackResult *)onDoneConnection:(Ssi_agentConnection *)connection credentialContainer:(Ssi_agentCredentialContainer *)credentialContainer __attribute__((swift_name("onDone(connection:credentialContainer:)")));
- (Ssi_agentCallbackResult *)onOfferReceivedConnection:(Ssi_agentConnection *)connection credentialOfferContainer:(Ssi_agentCredentialOfferContainer *)credentialOfferContainer __attribute__((swift_name("onOfferReceived(connection:credentialOfferContainer:)")));
- (Ssi_agentCallbackResult *)onRequestSentConnection:(Ssi_agentConnection *)connection credentialRequestContainer:(Ssi_agentCredentialRequestContainer *)credentialRequestContainer __attribute__((swift_name("onRequestSent(connection:credentialRequestContainer:)")));
@end;

__attribute__((swift_name("ConnectionInitiatorController")))
@protocol Ssi_agentConnectionInitiatorController
@required
- (Ssi_agentCallbackResult *)onAbandonedConnection:(Ssi_agentConnection *)connection problemReport:(Ssi_agentProblemReport *)problemReport __attribute__((swift_name("onAbandoned(connection:problemReport:)")));
- (Ssi_agentCallbackResult *)onCompletedConnection:(Ssi_agentConnection *)connection __attribute__((swift_name("onCompleted(connection:)")));
- (Ssi_agentCallbackResult *)onInvitationReceivedConnection:(Ssi_agentConnection *)connection endpoint:(NSString *)endpoint invitation:(Ssi_agentInvitation *)invitation __attribute__((swift_name("onInvitationReceived(connection:endpoint:invitation:)")));
- (Ssi_agentCallbackResult *)onRequestSentConnection:(Ssi_agentConnection *)connection request:(Ssi_agentConnectionRequest *)request __attribute__((swift_name("onRequestSent(connection:request:)")));
- (Ssi_agentCallbackResult *)onResponseReceivedConnection:(Ssi_agentConnection *)connection response:(Ssi_agentConnectionResponse *)response __attribute__((swift_name("onResponseReceived(connection:response:)")));
@end;

__attribute__((swift_name("ConnectionResponderController")))
@protocol Ssi_agentConnectionResponderController
@required
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("EnvironmentImpl")))
@interface Ssi_agentEnvironmentImpl : Ssi_agentBase <Ssi_agentEnvironment>
- (instancetype)init __attribute__((swift_name("init()"))) __attribute__((objc_designated_initializer));
+ (instancetype)new __attribute__((availability(swift, unavailable, message="use object initializers instead")));
- (NSString *)getWritableFolderInUserHome __attribute__((swift_name("getWritableFolderInUserHome()")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("SsiAgentApiImpl")))
@interface Ssi_agentSsiAgentApiImpl : Ssi_agentBase <Ssi_agentSsiAgentApi>
- (instancetype)initWithTransport:(id<Ssi_agentTransport>)transport walletConnector:(Ssi_agentWalletConnector *)walletConnector ledgerConnector:(id<Ssi_agentLedgerConnector>)ledgerConnector callbacks:(Ssi_agentCallbacks *)callbacks environment:(id<Ssi_agentEnvironment>)environment __attribute__((swift_name("init(transport:walletConnector:ledgerConnector:callbacks:environment:)"))) __attribute__((objc_designated_initializer));
- (Ssi_agentConnection *)connectUrl:(NSString *)url __attribute__((swift_name("connect(url:)")));
- (void)disconnectConnection:(Ssi_agentConnection *)connection __attribute__((swift_name("disconnect(connection:)")));
- (void)disconnectConnection:(Ssi_agentConnection *)connection force:(BOOL)force __attribute__((swift_name("disconnect(connection:force:)")));
- (void)disconnectAllForce:(BOOL)force __attribute__((swift_name("disconnectAll(force:)")));
- (NSSet<Ssi_agentConnection *> *)getConnections __attribute__((swift_name("getConnections()")));
- (id<Ssi_agentLedgerConnector>)getLedgerConnector __attribute__((swift_name("getLedgerConnector()")));
- (Ssi_agentWalletConnector *)getWalletConnector __attribute__((swift_name("getWalletConnector()")));
- (void)doInit __attribute__((swift_name("doInit()")));
- (void)issueCredentialOverConnectionConnection:(Ssi_agentConnection *)connection __attribute__((swift_name("issueCredentialOverConnection(connection:)")));
- (void)requestProofOverConnectionConnection:(Ssi_agentConnection *)connection __attribute__((swift_name("requestProofOverConnection(connection:)")));
- (BOOL)sendTrustPingConnection:(Ssi_agentConnection *)connection __attribute__((swift_name("sendTrustPing(connection:)")));
- (void)shutdownForce:(BOOL)force __attribute__((swift_name("shutdown(force:)")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("SsiAgentBuilderImpl")))
@interface Ssi_agentSsiAgentBuilderImpl : Ssi_agentBase <Ssi_agentSsiAgentBuilder>
- (instancetype)init __attribute__((swift_name("init()"))) __attribute__((objc_designated_initializer));
+ (instancetype)new __attribute__((availability(swift, unavailable, message="use object initializers instead")));
- (id<Ssi_agentSsiAgentApi>)build __attribute__((swift_name("build()")));
- (id<Ssi_agentSsiAgentBuilder>)withConnectionInitiatorControllerConnectionInitiatorController:(id<Ssi_agentConnectionInitiatorController>)connectionInitiatorController __attribute__((swift_name("withConnectionInitiatorController(connectionInitiatorController:)")));
- (id<Ssi_agentSsiAgentBuilder>)withConnectionResponderControllerConnectionResponderController:(id<Ssi_agentConnectionResponderController>)connectionResponderController __attribute__((swift_name("withConnectionResponderController(connectionResponderController:)")));
- (id<Ssi_agentSsiAgentBuilder>)withCredIssuerControllerCredIssuerController:(id<Ssi_agentCredIssuerController>)credIssuerController __attribute__((swift_name("withCredIssuerController(credIssuerController:)")));
- (id<Ssi_agentSsiAgentBuilder>)withCredPresenterControllerCredPresenterController:(id<Ssi_agentCredPresenterController>)credPresenterController __attribute__((swift_name("withCredPresenterController(credPresenterController:)")));
- (id<Ssi_agentSsiAgentBuilder>)withCredReceiverControllerCredReceiverController:(id<Ssi_agentCredReceiverController>)credReceiverController __attribute__((swift_name("withCredReceiverController(credReceiverController:)")));
- (id<Ssi_agentSsiAgentBuilder>)withCredVerifierControllerCredVerifierController:(id<Ssi_agentCredVerifierController>)credVerifierController __attribute__((swift_name("withCredVerifierController(credVerifierController:)")));
- (id<Ssi_agentSsiAgentBuilder>)withEnvironmentEnvironment:(id<Ssi_agentEnvironment>)environment __attribute__((swift_name("withEnvironment(environment:)")));
- (id<Ssi_agentSsiAgentBuilder>)withIssuerIssuer:(id<Ssi_agentIssuer>)issuer __attribute__((swift_name("withIssuer(issuer:)")));
- (id<Ssi_agentSsiAgentBuilder>)withLedgerConnectorLedgerConnector:(id<Ssi_agentLedgerConnector>)ledgerConnector __attribute__((swift_name("withLedgerConnector(ledgerConnector:)")));
- (id<Ssi_agentSsiAgentBuilder>)withProverProver:(id<Ssi_agentProver>)prover __attribute__((swift_name("withProver(prover:)")));
- (id<Ssi_agentSsiAgentBuilder>)withTransportTransport:(id<Ssi_agentTransport>)transport __attribute__((swift_name("withTransport(transport:)")));
- (id<Ssi_agentSsiAgentBuilder>)withTrusteeTrustee:(id<Ssi_agentTrustee>)trustee __attribute__((swift_name("withTrustee(trustee:)")));
- (id<Ssi_agentSsiAgentBuilder>)withVerifierVerifier:(id<Ssi_agentVerifier>)verifier __attribute__((swift_name("withVerifier(verifier:)")));
- (id<Ssi_agentSsiAgentBuilder>)withWalletHolderWalletHolder:(id<Ssi_agentWalletHolder>)walletHolder __attribute__((swift_name("withWalletHolder(walletHolder:)")));
@end;

__attribute__((swift_name("LedgerConnector")))
@protocol Ssi_agentLedgerConnector
@required

/**
 @note This method converts instances of CancellationException to errors.
 Other uncaught Kotlin exceptions are fatal.
*/
- (void)doInitWithCompletionHandler:(void (^)(Ssi_agentKotlinUnit * _Nullable, NSError * _Nullable))completionHandler __attribute__((swift_name("doInit(completionHandler:)")));

/**
 @note This method converts instances of CancellationException to errors.
 Other uncaught Kotlin exceptions are fatal.
*/
- (void)retrieveCredentialDefinitionId:(id<Ssi_agentCredentialDefinitionId>)id delayMs:(int64_t)delayMs retryTimes:(int32_t)retryTimes completionHandler:(void (^)(id<Ssi_agentCredentialDefinition> _Nullable, NSError * _Nullable))completionHandler __attribute__((swift_name("retrieveCredentialDefinition(id:delayMs:retryTimes:completionHandler:)")));

/**
 @note This method converts instances of CancellationException to errors.
 Other uncaught Kotlin exceptions are fatal.
*/
- (void)retrieveRevocationRegistryDefinitionId:(Ssi_agentRevocationRegistryDefinitionId *)id delayMs:(int64_t)delayMs retryTimes:(int32_t)retryTimes completionHandler:(void (^)(id<Ssi_agentRevocationRegistryDefinition> _Nullable, NSError * _Nullable))completionHandler __attribute__((swift_name("retrieveRevocationRegistryDefinition(id:delayMs:retryTimes:completionHandler:)")));

/**
 @note This method converts instances of CancellationException to errors.
 Other uncaught Kotlin exceptions are fatal.
*/
- (void)retrieveRevocationRegistryDeltaId:(Ssi_agentRevocationRegistryDefinitionId *)id interval:(Ssi_agentInterval *)interval delayMs:(int64_t)delayMs retryTimes:(int32_t)retryTimes completionHandler:(void (^)(Ssi_agentKotlinPair<Ssi_agentLong *, Ssi_agentRevocationRegistryEntry *> * _Nullable, NSError * _Nullable))completionHandler __attribute__((swift_name("retrieveRevocationRegistryDelta(id:interval:delayMs:retryTimes:completionHandler:)")));

/**
 @note This method converts instances of CancellationException to errors.
 Other uncaught Kotlin exceptions are fatal.
*/
- (void)retrieveSchemaId:(id<Ssi_agentSchemaId>)id delayMs:(int64_t)delayMs retryTimes:(int32_t)retryTimes completionHandler:(void (^)(id<Ssi_agentSchema> _Nullable, NSError * _Nullable))completionHandler __attribute__((swift_name("retrieveSchema(id:delayMs:retryTimes:completionHandler:)")));
@property NSString *did __attribute__((swift_name("did")));
@end;

__attribute__((swift_name("Issuer")))
@protocol Ssi_agentIssuer
@required
- (NSString *)signData:(NSString *)data __attribute__((swift_name("sign(data:)")));
@end;

__attribute__((swift_name("Prover")))
@protocol Ssi_agentProver
@required
- (id<Ssi_agentCredential>)buildCredentialObjectFromRawDataData:(Ssi_agentData *)data __attribute__((swift_name("buildCredentialObjectFromRawData(data:)")));
- (id<Ssi_agentCredentialOffer>)buildCredentialOfferObjectFromRawDataData:(Ssi_agentData *)data __attribute__((swift_name("buildCredentialOfferObjectFromRawData(data:)")));
- (id<Ssi_agentPresentationRequest>)buildPresentationRequestObjectFromRawDataData:(Ssi_agentData *)data __attribute__((swift_name("buildPresentationRequestObjectFromRawData(data:)")));
- (id<Ssi_agentCredentialDefinitionId>)createCredentialDefinitionIdFromOfferCredentialOffer:(id<Ssi_agentCredentialOffer>)credentialOffer __attribute__((swift_name("createCredentialDefinitionIdFromOffer(credentialOffer:)")));

/**
 @note This method converts instances of CancellationException to errors.
 Other uncaught Kotlin exceptions are fatal.
*/
- (void)createCredentialRequestProverDid:(NSString *)proverDid credentialDefinition:(id<Ssi_agentCredentialDefinition>)credentialDefinition credentialOffer:(id<Ssi_agentCredentialOffer>)credentialOffer masterSecretId:(NSString *)masterSecretId completionHandler:(void (^)(Ssi_agentCredentialRequestInfo * _Nullable, NSError * _Nullable))completionHandler __attribute__((swift_name("createCredentialRequest(proverDid:credentialDefinition:credentialOffer:masterSecretId:completionHandler:)")));

/**
 @note This method converts instances of CancellationException to errors.
 Other uncaught Kotlin exceptions are fatal.
*/
- (void)createMasterSecretId:(NSString *)id completionHandler:(void (^)(Ssi_agentKotlinUnit * _Nullable, NSError * _Nullable))completionHandler __attribute__((swift_name("createMasterSecret(id:completionHandler:)")));

/**
 @note This method converts instances of CancellationException to errors.
 Other uncaught Kotlin exceptions are fatal.
*/
- (void)createPresentationPresentationRequest:(id<Ssi_agentPresentationRequest>)presentationRequest ledgerConnector:(id<Ssi_agentLedgerConnector>)ledgerConnector completionHandler:(void (^)(id<Ssi_agentPresentation> _Nullable, NSError * _Nullable))completionHandler __attribute__((swift_name("createPresentation(presentationRequest:ledgerConnector:completionHandler:)")));

/**
 @note This method converts instances of CancellationException to errors.
 Other uncaught Kotlin exceptions are fatal.
*/
- (void)createRevocationStateRevocationRegistryDefinition:(id<Ssi_agentRevocationRegistryDefinition>)revocationRegistryDefinition revocationRegistryEntry:(Ssi_agentRevocationRegistryEntry *)revocationRegistryEntry credentialRevocationId:(NSString *)credentialRevocationId timestamp:(int64_t)timestamp completionHandler:(void (^)(Ssi_agentRevocationState * _Nullable, NSError * _Nullable))completionHandler __attribute__((swift_name("createRevocationState(revocationRegistryDefinition:revocationRegistryEntry:credentialRevocationId:timestamp:completionHandler:)")));
- (Ssi_agentData *)extractCredentialRequestDataFromCredentialInfoCredentialRequestInfo:(Ssi_agentCredentialRequestInfo *)credentialRequestInfo __attribute__((swift_name("extractCredentialRequestDataFromCredentialInfo(credentialRequestInfo:)")));
- (Ssi_agentData *)extractPresentationDataFromPresentationPresentation:(id<Ssi_agentPresentation>)presentation __attribute__((swift_name("extractPresentationDataFromPresentation(presentation:)")));

/**
 @note This method converts instances of CancellationException to errors.
 Other uncaught Kotlin exceptions are fatal.
*/
- (void)getCredentialExchangeRecordByThreadThread:(Ssi_agentThread *)thread completionHandler:(void (^)(Ssi_agentCredentialExchangeRecord * _Nullable, NSError * _Nullable))completionHandler __attribute__((swift_name("getCredentialExchangeRecordByThread(thread:completionHandler:)")));

/**
 @note This method converts instances of CancellationException to errors.
 Other uncaught Kotlin exceptions are fatal.
*/
- (void)receiveCredentialCredential:(id<Ssi_agentCredential>)credential credentialRequestInfo:(Ssi_agentCredentialRequestInfo *)credentialRequestInfo credentialDefinition:(id<Ssi_agentCredentialDefinition>)credentialDefinition revocationRegistryDefinition:(id<Ssi_agentRevocationRegistryDefinition> _Nullable)revocationRegistryDefinition completionHandler:(void (^)(NSString * _Nullable, NSError * _Nullable))completionHandler __attribute__((swift_name("receiveCredential(credential:credentialRequestInfo:credentialDefinition:revocationRegistryDefinition:completionHandler:)")));

/**
 @note This method converts instances of CancellationException to errors.
 Other uncaught Kotlin exceptions are fatal.
*/
- (void)removeCredentialExchangeRecordByThreadThread:(Ssi_agentThread *)thread completionHandler:(void (^)(Ssi_agentKotlinUnit * _Nullable, NSError * _Nullable))completionHandler __attribute__((swift_name("removeCredentialExchangeRecordByThread(thread:completionHandler:)")));

/**
 @note This method converts instances of CancellationException to errors.
 Other uncaught Kotlin exceptions are fatal.
*/
- (void)storeCredentialExchangeRecordCredentialExchangeRecord:(Ssi_agentCredentialExchangeRecord *)credentialExchangeRecord completionHandler:(void (^)(Ssi_agentKotlinUnit * _Nullable, NSError * _Nullable))completionHandler __attribute__((swift_name("storeCredentialExchangeRecord(credentialExchangeRecord:completionHandler:)")));
@end;

__attribute__((swift_name("Trustee")))
@protocol Ssi_agentTrustee
@required
@end;

__attribute__((swift_name("Verifier")))
@protocol Ssi_agentVerifier
@required
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("WalletConnector")))
@interface Ssi_agentWalletConnector : Ssi_agentBase
- (instancetype)initWithIssuer:(id<Ssi_agentIssuer> _Nullable)issuer prover:(id<Ssi_agentProver> _Nullable)prover verifier:(id<Ssi_agentVerifier> _Nullable)verifier trustee:(id<Ssi_agentTrustee> _Nullable)trustee walletHolder:(id<Ssi_agentWalletHolder>)walletHolder __attribute__((swift_name("init(issuer:prover:verifier:trustee:walletHolder:)"))) __attribute__((objc_designated_initializer));
- (id<Ssi_agentIssuer> _Nullable)component1 __attribute__((swift_name("component1()")));
- (id<Ssi_agentProver> _Nullable)component2 __attribute__((swift_name("component2()")));
- (id<Ssi_agentVerifier> _Nullable)component3 __attribute__((swift_name("component3()")));
- (id<Ssi_agentTrustee> _Nullable)component4 __attribute__((swift_name("component4()")));
- (id<Ssi_agentWalletHolder>)component5 __attribute__((swift_name("component5()")));
- (Ssi_agentWalletConnector *)doCopyIssuer:(id<Ssi_agentIssuer> _Nullable)issuer prover:(id<Ssi_agentProver> _Nullable)prover verifier:(id<Ssi_agentVerifier> _Nullable)verifier trustee:(id<Ssi_agentTrustee> _Nullable)trustee walletHolder:(id<Ssi_agentWalletHolder>)walletHolder __attribute__((swift_name("doCopy(issuer:prover:verifier:trustee:walletHolder:)")));
- (BOOL)isEqual:(id _Nullable)other __attribute__((swift_name("isEqual(_:)")));
- (NSUInteger)hash __attribute__((swift_name("hash()")));
- (NSString *)description __attribute__((swift_name("description()")));
@property (readonly) id<Ssi_agentIssuer> _Nullable issuer __attribute__((swift_name("issuer")));
@property (readonly) id<Ssi_agentProver> _Nullable prover __attribute__((swift_name("prover")));
@property (readonly) id<Ssi_agentTrustee> _Nullable trustee __attribute__((swift_name("trustee")));
@property (readonly) id<Ssi_agentVerifier> _Nullable verifier __attribute__((swift_name("verifier")));
@property (readonly) id<Ssi_agentWalletHolder> walletHolder __attribute__((swift_name("walletHolder")));
@end;

__attribute__((swift_name("WalletHolder")))
@protocol Ssi_agentWalletHolder
@required
- (NSString *)createSessionDidIdentityRecord:(Ssi_agentIdentityDetails *)identityRecord __attribute__((swift_name("createSessionDid(identityRecord:)")));

/**
 @note This method converts instances of CancellationException to errors.
 Other uncaught Kotlin exceptions are fatal.
*/
- (void)findConnectionByVerKeyVerKey:(NSString *)verKey completionHandler:(void (^)(Ssi_agentConnection * _Nullable, NSError * _Nullable))completionHandler __attribute__((swift_name("findConnectionByVerKey(verKey:completionHandler:)")));

/**
 @note This method converts instances of CancellationException to errors.
 Other uncaught Kotlin exceptions are fatal.
*/
- (void)getConnectionRecordByIdConnectionId:(NSString *)connectionId completionHandler:(void (^)(Ssi_agentConnection * _Nullable, NSError * _Nullable))completionHandler __attribute__((swift_name("getConnectionRecordById(connectionId:completionHandler:)")));
- (Ssi_agentIdentityDetails *)getIdentityDetails __attribute__((swift_name("getIdentityDetails()")));
- (Ssi_agentIdentityDetails *)getIdentityDetailsDid:(NSString *)did __attribute__((swift_name("getIdentityDetails(did:)")));
- (NSString *)getTailsPath __attribute__((swift_name("getTailsPath()")));
- (id)getWallet __attribute__((swift_name("getWallet()")));

/**
 @note This method converts instances of CancellationException to errors.
 Other uncaught Kotlin exceptions are fatal.
*/
- (void)openOrCreateWalletWithCompletionHandler:(void (^)(Ssi_agentKotlinUnit * _Nullable, NSError * _Nullable))completionHandler __attribute__((swift_name("openOrCreateWallet(completionHandler:)")));

/**
 @note This method converts instances of CancellationException to errors.
 Other uncaught Kotlin exceptions are fatal.
*/
- (void)packMessageMessage:(Ssi_agentMessage *)message recipientKeys:(NSArray<NSString *> *)recipientKeys useAnonCrypt:(BOOL)useAnonCrypt completionHandler:(void (^)(NSString * _Nullable, NSError * _Nullable))completionHandler __attribute__((swift_name("packMessage(message:recipientKeys:useAnonCrypt:completionHandler:)")));

/**
 @note This method converts instances of CancellationException to errors.
 Other uncaught Kotlin exceptions are fatal.
*/
- (void)storeConnectionRecordConnection:(Ssi_agentConnection *)connection completionHandler:(void (^)(Ssi_agentKotlinUnit * _Nullable, NSError * _Nullable))completionHandler __attribute__((swift_name("storeConnectionRecord(connection:completionHandler:)")));

/**
 @note This method converts instances of CancellationException to errors.
 Other uncaught Kotlin exceptions are fatal.
*/
- (void)unPackMessagePackedMessage:(Ssi_agentMessage *)packedMessage completionHandler:(void (^)(Ssi_agentMessage * _Nullable, NSError * _Nullable))completionHandler __attribute__((swift_name("unPackMessage(packedMessage:completionHandler:)")));
@end;

__attribute__((swift_name("CallbackData")))
@protocol Ssi_agentCallbackData
@required
@property (readonly) int32_t commandHandle __attribute__((swift_name("commandHandle")));
@property (readonly) uint32_t errorCode __attribute__((swift_name("errorCode")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("CallbackDataWrapper")))
@interface Ssi_agentCallbackDataWrapper : Ssi_agentBase
- (instancetype)initWithCallbackData:(id<Ssi_agentCallbackData>)callbackData indyException:(Ssi_agentIndyException * _Nullable)indyException __attribute__((swift_name("init(callbackData:indyException:)"))) __attribute__((objc_designated_initializer));
- (id<Ssi_agentCallbackData>)component1 __attribute__((swift_name("component1()")));
- (Ssi_agentIndyException * _Nullable)component2 __attribute__((swift_name("component2()")));
- (Ssi_agentCallbackDataWrapper *)doCopyCallbackData:(id<Ssi_agentCallbackData>)callbackData indyException:(Ssi_agentIndyException * _Nullable)indyException __attribute__((swift_name("doCopy(callbackData:indyException:)")));
- (BOOL)isEqual:(id _Nullable)other __attribute__((swift_name("isEqual(_:)")));
- (NSUInteger)hash __attribute__((swift_name("hash()")));
- (NSString *)description __attribute__((swift_name("description()")));
@property (readonly) id<Ssi_agentCallbackData> callbackData __attribute__((swift_name("callbackData")));
@property (readonly) Ssi_agentIndyException * _Nullable indyException __attribute__((swift_name("indyException")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("CallbackHandler")))
@interface Ssi_agentCallbackHandler : Ssi_agentBase
- (instancetype)init __attribute__((swift_name("init()"))) __attribute__((objc_designated_initializer));
+ (instancetype)new __attribute__((availability(swift, unavailable, message="use object initializers instead")));
- (int32_t)prepareCallback __attribute__((swift_name("prepareCallback()")));
- (void)setCallbackResultCallbackData:(id<Ssi_agentCallbackData>)callbackData __attribute__((swift_name("setCallbackResult(callbackData:)")));

/**
 @note This method converts instances of CancellationException to errors.
 Other uncaught Kotlin exceptions are fatal.
*/
- (void)waitForCallbackResultCommandHandle:(int32_t)commandHandle completionHandler:(void (^)(id<Ssi_agentCallbackData> _Nullable, NSError * _Nullable))completionHandler __attribute__((swift_name("waitForCallbackResult(commandHandle:completionHandler:)")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("CallbackHandler.Companion")))
@interface Ssi_agentCallbackHandlerCompanion : Ssi_agentBase
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
+ (instancetype)companion __attribute__((swift_name("init()")));
@property (readonly) Ssi_agentKotlinAtomicInt *commandHandleCounter __attribute__((swift_name("commandHandleCounter")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("IntCallback")))
@interface Ssi_agentIntCallback : Ssi_agentBase
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
+ (instancetype)intCallback __attribute__((swift_name("init()")));
@property (readonly) void *callback __attribute__((swift_name("callback")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("IntCallback.Result")))
@interface Ssi_agentIntCallbackResult : Ssi_agentBase <Ssi_agentCallbackData>
- (instancetype)initWithCommandHandle:(int32_t)commandHandle errorCode:(uint32_t)errorCode handle:(int32_t)handle __attribute__((swift_name("init(commandHandle:errorCode:handle:)"))) __attribute__((objc_designated_initializer));
- (int32_t)component1 __attribute__((swift_name("component1()")));
- (uint32_t)component2 __attribute__((swift_name("component2()")));
- (int32_t)component3 __attribute__((swift_name("component3()")));
- (Ssi_agentIntCallbackResult *)doCopyCommandHandle:(int32_t)commandHandle errorCode:(uint32_t)errorCode handle:(int32_t)handle __attribute__((swift_name("doCopy(commandHandle:errorCode:handle:)")));
- (BOOL)isEqual:(id _Nullable)other __attribute__((swift_name("isEqual(_:)")));
- (NSUInteger)hash __attribute__((swift_name("hash()")));
- (NSString *)description __attribute__((swift_name("description()")));
@property (readonly) int32_t commandHandle __attribute__((swift_name("commandHandle")));
@property (readonly) uint32_t errorCode __attribute__((swift_name("errorCode")));
@property (readonly) int32_t handle __attribute__((swift_name("handle")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("SimpleCallback")))
@interface Ssi_agentSimpleCallback : Ssi_agentBase
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
+ (instancetype)simpleCallback __attribute__((swift_name("init()")));
@property (readonly) void *callback __attribute__((swift_name("callback")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("SimpleCallback.Result")))
@interface Ssi_agentSimpleCallbackResult : Ssi_agentBase <Ssi_agentCallbackData>
- (instancetype)initWithCommandHandle:(int32_t)commandHandle errorCode:(uint32_t)errorCode __attribute__((swift_name("init(commandHandle:errorCode:)"))) __attribute__((objc_designated_initializer));
- (int32_t)component1 __attribute__((swift_name("component1()")));
- (uint32_t)component2 __attribute__((swift_name("component2()")));
- (Ssi_agentSimpleCallbackResult *)doCopyCommandHandle:(int32_t)commandHandle errorCode:(uint32_t)errorCode __attribute__((swift_name("doCopy(commandHandle:errorCode:)")));
- (BOOL)isEqual:(id _Nullable)other __attribute__((swift_name("isEqual(_:)")));
- (NSUInteger)hash __attribute__((swift_name("hash()")));
- (NSString *)description __attribute__((swift_name("description()")));
@property (readonly) int32_t commandHandle __attribute__((swift_name("commandHandle")));
@property (readonly) uint32_t errorCode __attribute__((swift_name("errorCode")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("StringCallback")))
@interface Ssi_agentStringCallback : Ssi_agentBase
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
+ (instancetype)stringCallback __attribute__((swift_name("init()")));
@property (readonly) void *callback __attribute__((swift_name("callback")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("StringCallback.Result")))
@interface Ssi_agentStringCallbackResult : Ssi_agentBase <Ssi_agentCallbackData>
- (instancetype)initWithCommandHandle:(int32_t)commandHandle errorCode:(uint32_t)errorCode stringResult:(NSString *)stringResult __attribute__((swift_name("init(commandHandle:errorCode:stringResult:)"))) __attribute__((objc_designated_initializer));
- (int32_t)component1 __attribute__((swift_name("component1()")));
- (uint32_t)component2 __attribute__((swift_name("component2()")));
- (NSString *)component3 __attribute__((swift_name("component3()")));
- (Ssi_agentStringCallbackResult *)doCopyCommandHandle:(int32_t)commandHandle errorCode:(uint32_t)errorCode stringResult:(NSString *)stringResult __attribute__((swift_name("doCopy(commandHandle:errorCode:stringResult:)")));
- (BOOL)isEqual:(id _Nullable)other __attribute__((swift_name("isEqual(_:)")));
- (NSUInteger)hash __attribute__((swift_name("hash()")));
- (NSString *)description __attribute__((swift_name("description()")));
@property (readonly) int32_t commandHandle __attribute__((swift_name("commandHandle")));
@property (readonly) uint32_t errorCode __attribute__((swift_name("errorCode")));
@property (readonly) NSString *stringResult __attribute__((swift_name("stringResult")));
@end;

__attribute__((swift_name("Kotlinx_serialization_coreSerializationStrategy")))
@protocol Ssi_agentKotlinx_serialization_coreSerializationStrategy
@required
- (void)serializeEncoder:(id<Ssi_agentKotlinx_serialization_coreEncoder>)encoder value:(id _Nullable)value __attribute__((swift_name("serialize(encoder:value:)")));
@property (readonly) id<Ssi_agentKotlinx_serialization_coreSerialDescriptor> descriptor __attribute__((swift_name("descriptor")));
@end;

__attribute__((swift_name("Kotlinx_serialization_coreDeserializationStrategy")))
@protocol Ssi_agentKotlinx_serialization_coreDeserializationStrategy
@required
- (id _Nullable)deserializeDecoder:(id<Ssi_agentKotlinx_serialization_coreDecoder>)decoder __attribute__((swift_name("deserialize(decoder:)")));
@property (readonly) id<Ssi_agentKotlinx_serialization_coreSerialDescriptor> descriptor __attribute__((swift_name("descriptor")));
@end;

__attribute__((swift_name("Kotlinx_serialization_coreKSerializer")))
@protocol Ssi_agentKotlinx_serialization_coreKSerializer <Ssi_agentKotlinx_serialization_coreSerializationStrategy, Ssi_agentKotlinx_serialization_coreDeserializationStrategy>
@required
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("AnyToStringSerializer")))
@interface Ssi_agentAnyToStringSerializer : Ssi_agentBase <Ssi_agentKotlinx_serialization_coreKSerializer>
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
+ (instancetype)anyToStringSerializer __attribute__((swift_name("init()")));
- (NSString *)deserializeDecoder:(id<Ssi_agentKotlinx_serialization_coreDecoder>)decoder __attribute__((swift_name("deserialize(decoder:)")));
- (void)serializeEncoder:(id<Ssi_agentKotlinx_serialization_coreEncoder>)encoder value:(NSString *)value __attribute__((swift_name("serialize(encoder:value:)")));
@property (readonly) id<Ssi_agentKotlinx_serialization_coreSerialDescriptor> descriptor __attribute__((swift_name("descriptor")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("CoroutineHelper")))
@interface Ssi_agentCoroutineHelper : Ssi_agentBase
- (instancetype)init __attribute__((swift_name("init()"))) __attribute__((objc_designated_initializer));
+ (instancetype)new __attribute__((availability(swift, unavailable, message="use object initializers instead")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("CoroutineHelper.Companion")))
@interface Ssi_agentCoroutineHelperCompanion : Ssi_agentBase
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
+ (instancetype)companion __attribute__((swift_name("init()")));
- (id<Ssi_agentKotlinCoroutineContext>)singleThreadCoroutineContextThreadName:(NSString *)threadName __attribute__((swift_name("singleThreadCoroutineContext(threadName:)")));
- (id _Nullable)waitForCompletionDeferred:(id<Ssi_agentKotlinx_coroutines_coreDeferred>)deferred __attribute__((swift_name("waitForCompletion(deferred:)")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("JsonUtils")))
@interface Ssi_agentJsonUtils : Ssi_agentBase
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
+ (instancetype)jsonUtils __attribute__((swift_name("init()")));
- (NSString *)extractValueRetrievedValue:(NSString * _Nullable)retrievedValue __attribute__((swift_name("extractValue(retrievedValue:)")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("ToBeReworked")))
@interface Ssi_agentToBeReworked : Ssi_agentBase
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
+ (instancetype)toBeReworked __attribute__((swift_name("init()")));
- (void)enableIndyLog __attribute__((swift_name("enableIndyLog()")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("IndySerializationUtils")))
@interface Ssi_agentIndySerializationUtils : Ssi_agentBase
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
+ (instancetype)indySerializationUtils __attribute__((swift_name("init()")));
@property (readonly) Ssi_agentKotlinx_serialization_jsonJson *jsonProcessor __attribute__((swift_name("jsonProcessor")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("ErrorCode")))
@interface Ssi_agentErrorCode : Ssi_agentKotlinEnum<Ssi_agentErrorCode *>
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
- (instancetype)initWithName:(NSString *)name ordinal:(int32_t)ordinal __attribute__((swift_name("init(name:ordinal:)"))) __attribute__((objc_designated_initializer)) __attribute__((unavailable));
@property (class, readonly) Ssi_agentErrorCode *success __attribute__((swift_name("success")));
@property (class, readonly) Ssi_agentErrorCode *commoninvalidparam1 __attribute__((swift_name("commoninvalidparam1")));
@property (class, readonly) Ssi_agentErrorCode *commoninvalidparam2 __attribute__((swift_name("commoninvalidparam2")));
@property (class, readonly) Ssi_agentErrorCode *commoninvalidparam3 __attribute__((swift_name("commoninvalidparam3")));
@property (class, readonly) Ssi_agentErrorCode *commoninvalidparam4 __attribute__((swift_name("commoninvalidparam4")));
@property (class, readonly) Ssi_agentErrorCode *commoninvalidparam5 __attribute__((swift_name("commoninvalidparam5")));
@property (class, readonly) Ssi_agentErrorCode *commoninvalidparam6 __attribute__((swift_name("commoninvalidparam6")));
@property (class, readonly) Ssi_agentErrorCode *commoninvalidparam7 __attribute__((swift_name("commoninvalidparam7")));
@property (class, readonly) Ssi_agentErrorCode *commoninvalidparam8 __attribute__((swift_name("commoninvalidparam8")));
@property (class, readonly) Ssi_agentErrorCode *commoninvalidparam9 __attribute__((swift_name("commoninvalidparam9")));
@property (class, readonly) Ssi_agentErrorCode *commoninvalidparam10 __attribute__((swift_name("commoninvalidparam10")));
@property (class, readonly) Ssi_agentErrorCode *commoninvalidparam11 __attribute__((swift_name("commoninvalidparam11")));
@property (class, readonly) Ssi_agentErrorCode *commoninvalidparam12 __attribute__((swift_name("commoninvalidparam12")));
@property (class, readonly) Ssi_agentErrorCode *commoninvalidstate __attribute__((swift_name("commoninvalidstate")));
@property (class, readonly) Ssi_agentErrorCode *commoninvalidstructure __attribute__((swift_name("commoninvalidstructure")));
@property (class, readonly) Ssi_agentErrorCode *commonioerror __attribute__((swift_name("commonioerror")));
@property (class, readonly) Ssi_agentErrorCode *commoninvalidparam13 __attribute__((swift_name("commoninvalidparam13")));
@property (class, readonly) Ssi_agentErrorCode *commoninvalidparam14 __attribute__((swift_name("commoninvalidparam14")));
@property (class, readonly) Ssi_agentErrorCode *walletinvalidhandle __attribute__((swift_name("walletinvalidhandle")));
@property (class, readonly) Ssi_agentErrorCode *walletunknowntypeerror __attribute__((swift_name("walletunknowntypeerror")));
@property (class, readonly) Ssi_agentErrorCode *wallettypealreadyregisterederror __attribute__((swift_name("wallettypealreadyregisterederror")));
@property (class, readonly) Ssi_agentErrorCode *walletalreadyexistserror __attribute__((swift_name("walletalreadyexistserror")));
@property (class, readonly) Ssi_agentErrorCode *walletnotfounderror __attribute__((swift_name("walletnotfounderror")));
@property (class, readonly) Ssi_agentErrorCode *walletincompatiblepoolerror __attribute__((swift_name("walletincompatiblepoolerror")));
@property (class, readonly) Ssi_agentErrorCode *walletalreadyopenederror __attribute__((swift_name("walletalreadyopenederror")));
@property (class, readonly) Ssi_agentErrorCode *walletaccessfailed __attribute__((swift_name("walletaccessfailed")));
@property (class, readonly) Ssi_agentErrorCode *walletinputerror __attribute__((swift_name("walletinputerror")));
@property (class, readonly) Ssi_agentErrorCode *walletdecodingerror __attribute__((swift_name("walletdecodingerror")));
@property (class, readonly) Ssi_agentErrorCode *walletstorageerror __attribute__((swift_name("walletstorageerror")));
@property (class, readonly) Ssi_agentErrorCode *walletencryptionerror __attribute__((swift_name("walletencryptionerror")));
@property (class, readonly) Ssi_agentErrorCode *walletitemnotfound __attribute__((swift_name("walletitemnotfound")));
@property (class, readonly) Ssi_agentErrorCode *walletitemalreadyexists __attribute__((swift_name("walletitemalreadyexists")));
@property (class, readonly) Ssi_agentErrorCode *walletqueryerror __attribute__((swift_name("walletqueryerror")));
@property (class, readonly) Ssi_agentErrorCode *poolledgernotcreatederror __attribute__((swift_name("poolledgernotcreatederror")));
@property (class, readonly) Ssi_agentErrorCode *poolledgerinvalidpoolhandle __attribute__((swift_name("poolledgerinvalidpoolhandle")));
@property (class, readonly) Ssi_agentErrorCode *poolledgerterminated __attribute__((swift_name("poolledgerterminated")));
@property (class, readonly) Ssi_agentErrorCode *ledgernoconsensuserror __attribute__((swift_name("ledgernoconsensuserror")));
@property (class, readonly) Ssi_agentErrorCode *ledgerinvalidtransaction __attribute__((swift_name("ledgerinvalidtransaction")));
@property (class, readonly) Ssi_agentErrorCode *ledgersecurityerror __attribute__((swift_name("ledgersecurityerror")));
@property (class, readonly) Ssi_agentErrorCode *poolledgerconfigalreadyexistserror __attribute__((swift_name("poolledgerconfigalreadyexistserror")));
@property (class, readonly) Ssi_agentErrorCode *poolledgertimeout __attribute__((swift_name("poolledgertimeout")));
@property (class, readonly) Ssi_agentErrorCode *poolincompatibleprotocolversion __attribute__((swift_name("poolincompatibleprotocolversion")));
@property (class, readonly) Ssi_agentErrorCode *ledgernotfound __attribute__((swift_name("ledgernotfound")));
@property (class, readonly) Ssi_agentErrorCode *anoncredsrevocationregistryfullerror __attribute__((swift_name("anoncredsrevocationregistryfullerror")));
@property (class, readonly) Ssi_agentErrorCode *anoncredsinvaliduserrevocid __attribute__((swift_name("anoncredsinvaliduserrevocid")));
@property (class, readonly) Ssi_agentErrorCode *anoncredsmastersecretduplicatenameerror __attribute__((swift_name("anoncredsmastersecretduplicatenameerror")));
@property (class, readonly) Ssi_agentErrorCode *anoncredsproofrejected __attribute__((swift_name("anoncredsproofrejected")));
@property (class, readonly) Ssi_agentErrorCode *anoncredscredentialrevoked __attribute__((swift_name("anoncredscredentialrevoked")));
@property (class, readonly) Ssi_agentErrorCode *anoncredscreddefalreadyexistserror __attribute__((swift_name("anoncredscreddefalreadyexistserror")));
@property (class, readonly) Ssi_agentErrorCode *unknowncryptotypeerror __attribute__((swift_name("unknowncryptotypeerror")));
@property (class, readonly) Ssi_agentErrorCode *didalreadyexistserror __attribute__((swift_name("didalreadyexistserror")));
@property (class, readonly) Ssi_agentErrorCode *unknownpaymentmethod __attribute__((swift_name("unknownpaymentmethod")));
@property (class, readonly) Ssi_agentErrorCode *incompatiblepaymenterror __attribute__((swift_name("incompatiblepaymenterror")));
@property (class, readonly) Ssi_agentErrorCode *insufficientfundserror __attribute__((swift_name("insufficientfundserror")));
@property (class, readonly) Ssi_agentErrorCode *paymentsourcedoesnotexisterror __attribute__((swift_name("paymentsourcedoesnotexisterror")));
@property (class, readonly) Ssi_agentErrorCode *paymentoperationnotsupportederror __attribute__((swift_name("paymentoperationnotsupportederror")));
@property (class, readonly) Ssi_agentErrorCode *extrafundserror __attribute__((swift_name("extrafundserror")));
@property (class, readonly) Ssi_agentErrorCode *transactionnotallowederror __attribute__((swift_name("transactionnotallowederror")));
+ (Ssi_agentKotlinArray<Ssi_agentErrorCode *> *)values __attribute__((swift_name("values()")));
- (int32_t)value __attribute__((swift_name("value()")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("ErrorCode.Companion")))
@interface Ssi_agentErrorCodeCompanion : Ssi_agentBase
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
+ (instancetype)companion __attribute__((swift_name("init()")));
- (Ssi_agentErrorCode * _Nullable)valueOfValue:(int32_t)value __attribute__((swift_name("valueOf(value:)")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("ErrorDetails")))
@interface Ssi_agentErrorDetails : Ssi_agentBase
- (instancetype)initWithMessage:(NSString * _Nullable)message backtrace:(NSString * _Nullable)backtrace __attribute__((swift_name("init(message:backtrace:)"))) __attribute__((objc_designated_initializer));
- (NSString * _Nullable)component1 __attribute__((swift_name("component1()")));
- (NSString * _Nullable)component2 __attribute__((swift_name("component2()")));
- (Ssi_agentErrorDetails *)doCopyMessage:(NSString * _Nullable)message backtrace:(NSString * _Nullable)backtrace __attribute__((swift_name("doCopy(message:backtrace:)")));
- (BOOL)isEqual:(id _Nullable)other __attribute__((swift_name("isEqual(_:)")));
- (NSUInteger)hash __attribute__((swift_name("hash()")));
- (NSString *)description __attribute__((swift_name("description()")));
@property (readonly) NSString * _Nullable backtrace __attribute__((swift_name("backtrace")));
@property (readonly) NSString * _Nullable message __attribute__((swift_name("message")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("ErrorDetails.Companion")))
@interface Ssi_agentErrorDetailsCompanion : Ssi_agentBase
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
+ (instancetype)companion __attribute__((swift_name("init()")));
- (Ssi_agentErrorDetails *)getErrorDetailsFromIndy __attribute__((swift_name("getErrorDetailsFromIndy()")));
- (id<Ssi_agentKotlinx_serialization_coreKSerializer>)serializer __attribute__((swift_name("serializer()")));
@end;

__attribute__((swift_name("KotlinThrowable")))
@interface Ssi_agentKotlinThrowable : Ssi_agentBase
- (instancetype)initWithMessage:(NSString * _Nullable)message __attribute__((swift_name("init(message:)"))) __attribute__((objc_designated_initializer));
- (instancetype)initWithCause:(Ssi_agentKotlinThrowable * _Nullable)cause __attribute__((swift_name("init(cause:)"))) __attribute__((objc_designated_initializer));
- (instancetype)init __attribute__((swift_name("init()"))) __attribute__((objc_designated_initializer));
+ (instancetype)new __attribute__((availability(swift, unavailable, message="use object initializers instead")));
- (instancetype)initWithMessage:(NSString * _Nullable)message cause:(Ssi_agentKotlinThrowable * _Nullable)cause __attribute__((swift_name("init(message:cause:)"))) __attribute__((objc_designated_initializer));
- (Ssi_agentKotlinArray<NSString *> *)getStackTrace __attribute__((swift_name("getStackTrace()")));
- (void)printStackTrace __attribute__((swift_name("printStackTrace()")));
- (NSString *)description __attribute__((swift_name("description()")));
@property (readonly) Ssi_agentKotlinThrowable * _Nullable cause __attribute__((swift_name("cause")));
@property (readonly) NSString * _Nullable message __attribute__((swift_name("message")));
@end;

__attribute__((swift_name("KotlinException")))
@interface Ssi_agentKotlinException : Ssi_agentKotlinThrowable
- (instancetype)init __attribute__((swift_name("init()"))) __attribute__((objc_designated_initializer));
+ (instancetype)new __attribute__((availability(swift, unavailable, message="use object initializers instead")));
- (instancetype)initWithMessage:(NSString * _Nullable)message __attribute__((swift_name("init(message:)"))) __attribute__((objc_designated_initializer));
- (instancetype)initWithMessage:(NSString * _Nullable)message cause:(Ssi_agentKotlinThrowable * _Nullable)cause __attribute__((swift_name("init(message:cause:)"))) __attribute__((objc_designated_initializer));
- (instancetype)initWithCause:(Ssi_agentKotlinThrowable * _Nullable)cause __attribute__((swift_name("init(cause:)"))) __attribute__((objc_designated_initializer));
@end;

__attribute__((swift_name("IndyException")))
@interface Ssi_agentIndyException : Ssi_agentKotlinException
- (instancetype)initWithMessage:(NSString *)message sdkErrorCode:(int32_t)sdkErrorCode __attribute__((swift_name("init(message:sdkErrorCode:)"))) __attribute__((objc_designated_initializer));
- (instancetype)init __attribute__((swift_name("init()"))) __attribute__((objc_designated_initializer)) __attribute__((unavailable));
+ (instancetype)new __attribute__((unavailable));
- (instancetype)initWithMessage:(NSString * _Nullable)message __attribute__((swift_name("init(message:)"))) __attribute__((objc_designated_initializer)) __attribute__((unavailable));
- (instancetype)initWithMessage:(NSString * _Nullable)message cause:(Ssi_agentKotlinThrowable * _Nullable)cause __attribute__((swift_name("init(message:cause:)"))) __attribute__((objc_designated_initializer)) __attribute__((unavailable));
- (instancetype)initWithCause:(Ssi_agentKotlinThrowable * _Nullable)cause __attribute__((swift_name("init(cause:)"))) __attribute__((objc_designated_initializer)) __attribute__((unavailable));
- (NSString *)description __attribute__((swift_name("description()")));
@property (readonly) NSString *message __attribute__((swift_name("message")));
@property (readonly) NSString * _Nullable sdkBackTrace __attribute__((swift_name("sdkBackTrace")));
@property (readonly) int32_t sdkErrorCode __attribute__((swift_name("sdkErrorCode")));
@property (readonly) NSString * _Nullable sdkMessage __attribute__((swift_name("sdkMessage")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("IndyException.Companion")))
@interface Ssi_agentIndyExceptionCompanion : Ssi_agentBase
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
+ (instancetype)companion __attribute__((swift_name("init()")));
- (Ssi_agentIndyException *)fromSdkErrorSdkErrorCode:(int32_t)sdkErrorCode __attribute__((swift_name("fromSdkError(sdkErrorCode:)")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("InvalidParameterException")))
@interface Ssi_agentInvalidParameterException : Ssi_agentIndyException
- (instancetype)initWithSdkErrorCode:(int32_t)sdkErrorCode __attribute__((swift_name("init(sdkErrorCode:)"))) __attribute__((objc_designated_initializer));
- (instancetype)initWithMessage:(NSString *)message sdkErrorCode:(int32_t)sdkErrorCode __attribute__((swift_name("init(message:sdkErrorCode:)"))) __attribute__((objc_designated_initializer)) __attribute__((unavailable));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("WalletExistsException")))
@interface Ssi_agentWalletExistsException : Ssi_agentIndyException
- (instancetype)init __attribute__((swift_name("init()"))) __attribute__((objc_designated_initializer));
+ (instancetype)new __attribute__((availability(swift, unavailable, message="use object initializers instead")));
- (instancetype)initWithMessage:(NSString *)message sdkErrorCode:(int32_t)sdkErrorCode __attribute__((swift_name("init(message:sdkErrorCode:)"))) __attribute__((objc_designated_initializer)) __attribute__((unavailable));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("WalletItemNotFoundException")))
@interface Ssi_agentWalletItemNotFoundException : Ssi_agentIndyException
- (instancetype)init __attribute__((swift_name("init()"))) __attribute__((objc_designated_initializer));
+ (instancetype)new __attribute__((availability(swift, unavailable, message="use object initializers instead")));
- (instancetype)initWithMessage:(NSString *)message sdkErrorCode:(int32_t)sdkErrorCode __attribute__((swift_name("init(message:sdkErrorCode:)"))) __attribute__((objc_designated_initializer)) __attribute__((unavailable));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("IndyLedgerConnector")))
@interface Ssi_agentIndyLedgerConnector : Ssi_agentBase <Ssi_agentLedgerConnector>
- (instancetype)initWithIndyLedgerConnectorConfiguration:(Ssi_agentIndyLedgerConnectorConfiguration *)indyLedgerConnectorConfiguration __attribute__((swift_name("init(indyLedgerConnectorConfiguration:)"))) __attribute__((objc_designated_initializer));

/**
 @note This method converts instances of CancellationException to errors.
 Other uncaught Kotlin exceptions are fatal.
*/
- (void)doInitWithCompletionHandler:(void (^)(Ssi_agentKotlinUnit * _Nullable, NSError * _Nullable))completionHandler __attribute__((swift_name("doInit(completionHandler:)")));

/**
 @note This method converts instances of CancellationException to errors.
 Other uncaught Kotlin exceptions are fatal.
*/
- (void)retrieveCredentialDefinitionId:(id<Ssi_agentCredentialDefinitionId>)id delayMs:(int64_t)delayMs retryTimes:(int32_t)retryTimes completionHandler:(void (^)(id<Ssi_agentCredentialDefinition> _Nullable, NSError * _Nullable))completionHandler __attribute__((swift_name("retrieveCredentialDefinition(id:delayMs:retryTimes:completionHandler:)")));

/**
 @note This method converts instances of CancellationException to errors.
 Other uncaught Kotlin exceptions are fatal.
*/
- (void)retrieveRevocationRegistryDefinitionId:(Ssi_agentRevocationRegistryDefinitionId *)id delayMs:(int64_t)delayMs retryTimes:(int32_t)retryTimes completionHandler:(void (^)(id<Ssi_agentRevocationRegistryDefinition> _Nullable, NSError * _Nullable))completionHandler __attribute__((swift_name("retrieveRevocationRegistryDefinition(id:delayMs:retryTimes:completionHandler:)")));

/**
 @note This method converts instances of CancellationException to errors.
 Other uncaught Kotlin exceptions are fatal.
*/
- (void)retrieveRevocationRegistryDeltaId:(Ssi_agentRevocationRegistryDefinitionId *)id interval:(Ssi_agentInterval *)interval delayMs:(int64_t)delayMs retryTimes:(int32_t)retryTimes completionHandler:(void (^)(Ssi_agentKotlinPair<Ssi_agentLong *, Ssi_agentRevocationRegistryEntry *> * _Nullable, NSError * _Nullable))completionHandler __attribute__((swift_name("retrieveRevocationRegistryDelta(id:interval:delayMs:retryTimes:completionHandler:)")));

/**
 @note This method converts instances of CancellationException to errors.
 Other uncaught Kotlin exceptions are fatal.
*/
- (void)retrieveSchemaId:(id<Ssi_agentSchemaId>)id delayMs:(int64_t)delayMs retryTimes:(int32_t)retryTimes completionHandler:(void (^)(id<Ssi_agentSchema> _Nullable, NSError * _Nullable))completionHandler __attribute__((swift_name("retrieveSchema(id:delayMs:retryTimes:completionHandler:)")));
@property NSString *did __attribute__((swift_name("did")));
@property (readonly) Ssi_agentIndyLedgerConnectorConfiguration *indyLedgerConnectorConfiguration __attribute__((swift_name("indyLedgerConnectorConfiguration")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("IndyLedgerConnectorConfiguration")))
@interface Ssi_agentIndyLedgerConnectorConfiguration : Ssi_agentBase
- (instancetype)initWithGenesisFilePath:(NSString *)genesisFilePath ipAddress:(NSString *)ipAddress genesisMode:(Ssi_agentIndyLedgerConnectorConfigurationGenesisMode *)genesisMode __attribute__((swift_name("init(genesisFilePath:ipAddress:genesisMode:)"))) __attribute__((objc_designated_initializer));
- (NSString *)component1 __attribute__((swift_name("component1()")));
- (NSString *)component2 __attribute__((swift_name("component2()")));
- (Ssi_agentIndyLedgerConnectorConfigurationGenesisMode *)component3 __attribute__((swift_name("component3()")));
- (Ssi_agentIndyLedgerConnectorConfiguration *)doCopyGenesisFilePath:(NSString *)genesisFilePath ipAddress:(NSString *)ipAddress genesisMode:(Ssi_agentIndyLedgerConnectorConfigurationGenesisMode *)genesisMode __attribute__((swift_name("doCopy(genesisFilePath:ipAddress:genesisMode:)")));
- (BOOL)isEqual:(id _Nullable)other __attribute__((swift_name("isEqual(_:)")));
- (NSUInteger)hash __attribute__((swift_name("hash()")));
- (NSString *)description __attribute__((swift_name("description()")));
@property (readonly) NSString *genesisFilePath __attribute__((swift_name("genesisFilePath")));
@property (readonly) Ssi_agentIndyLedgerConnectorConfigurationGenesisMode *genesisMode __attribute__((swift_name("genesisMode")));
@property (readonly) NSString *ipAddress __attribute__((swift_name("ipAddress")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("IndyLedgerConnectorConfiguration.GenesisMode")))
@interface Ssi_agentIndyLedgerConnectorConfigurationGenesisMode : Ssi_agentKotlinEnum<Ssi_agentIndyLedgerConnectorConfigurationGenesisMode *>
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
- (instancetype)initWithName:(NSString *)name ordinal:(int32_t)ordinal __attribute__((swift_name("init(name:ordinal:)"))) __attribute__((objc_designated_initializer)) __attribute__((unavailable));
@property (class, readonly) Ssi_agentIndyLedgerConnectorConfigurationGenesisMode *ip __attribute__((swift_name("ip")));
@property (class, readonly) Ssi_agentIndyLedgerConnectorConfigurationGenesisMode *file __attribute__((swift_name("file")));
+ (Ssi_agentKotlinArray<Ssi_agentIndyLedgerConnectorConfigurationGenesisMode *> *)values __attribute__((swift_name("values()")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("Ledger")))
@interface Ssi_agentLedger : Ssi_agentBase
- (instancetype)init __attribute__((swift_name("init()"))) __attribute__((objc_designated_initializer));
+ (instancetype)new __attribute__((availability(swift, unavailable, message="use object initializers instead")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("Ledger.Companion")))
@interface Ssi_agentLedgerCompanion : Ssi_agentBase
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
+ (instancetype)companion __attribute__((swift_name("init()")));

/**
 @note This method converts instances of CancellationException to errors.
 Other uncaught Kotlin exceptions are fatal.
*/
- (void)buildGetCredDefRequestSubmitterDid:(NSString *)submitterDid id:(NSString *)id completionHandler:(void (^)(NSString * _Nullable, NSError * _Nullable))completionHandler __attribute__((swift_name("buildGetCredDefRequest(submitterDid:id:completionHandler:)")));

/**
 @note This method converts instances of CancellationException to errors.
 Other uncaught Kotlin exceptions are fatal.
*/
- (void)buildGetRevocRegDefRequestSubmitterDid:(NSString *)submitterDid id:(NSString *)id completionHandler:(void (^)(NSString * _Nullable, NSError * _Nullable))completionHandler __attribute__((swift_name("buildGetRevocRegDefRequest(submitterDid:id:completionHandler:)")));

/**
 @note This method converts instances of CancellationException to errors.
 Other uncaught Kotlin exceptions are fatal.
*/
- (void)buildGetRevocRegDeltaRequestSubmitterDid:(NSString *)submitterDid revocRegDefId:(NSString *)revocRegDefId from:(int64_t)from to:(int64_t)to completionHandler:(void (^)(NSString * _Nullable, NSError * _Nullable))completionHandler __attribute__((swift_name("buildGetRevocRegDeltaRequest(submitterDid:revocRegDefId:from:to:completionHandler:)")));

/**
 @note This method converts instances of CancellationException to errors.
 Other uncaught Kotlin exceptions are fatal.
*/
- (void)buildGetSchemaRequestSubmitterDid:(NSString *)submitterDid id:(NSString *)id completionHandler:(void (^)(NSString * _Nullable, NSError * _Nullable))completionHandler __attribute__((swift_name("buildGetSchemaRequest(submitterDid:id:completionHandler:)")));

/**
 @note This method converts instances of CancellationException to errors.
 Other uncaught Kotlin exceptions are fatal.
*/
- (void)parseGetCredDefResponseGetCredDefResponse:(NSString *)getCredDefResponse completionHandler:(void (^)(Ssi_agentParseResponseResult * _Nullable, NSError * _Nullable))completionHandler __attribute__((swift_name("parseGetCredDefResponse(getCredDefResponse:completionHandler:)")));

/**
 @note This method converts instances of CancellationException to errors.
 Other uncaught Kotlin exceptions are fatal.
*/
- (void)parseGetRevocRegDefResponseGetRevocRegDefResponse:(NSString *)getRevocRegDefResponse completionHandler:(void (^)(Ssi_agentParseResponseResult * _Nullable, NSError * _Nullable))completionHandler __attribute__((swift_name("parseGetRevocRegDefResponse(getRevocRegDefResponse:completionHandler:)")));

/**
 @note This method converts instances of CancellationException to errors.
 Other uncaught Kotlin exceptions are fatal.
*/
- (void)parseGetRevocRegDeltaResponseGetRevocRegDeltaResponse:(NSString *)getRevocRegDeltaResponse completionHandler:(void (^)(Ssi_agentParseRegistryResponseResult * _Nullable, NSError * _Nullable))completionHandler __attribute__((swift_name("parseGetRevocRegDeltaResponse(getRevocRegDeltaResponse:completionHandler:)")));

/**
 @note This method converts instances of CancellationException to errors.
 Other uncaught Kotlin exceptions are fatal.
*/
- (void)parseGetSchemaResponseGetSchemaResponse:(NSString *)getSchemaResponse completionHandler:(void (^)(Ssi_agentParseResponseResult * _Nullable, NSError * _Nullable))completionHandler __attribute__((swift_name("parseGetSchemaResponse(getSchemaResponse:completionHandler:)")));

/**
 @note This method converts instances of CancellationException to errors.
 Other uncaught Kotlin exceptions are fatal.
*/
- (void)submitRequestPool:(Ssi_agentPool *)pool requestJson:(NSString *)requestJson completionHandler:(void (^)(NSString * _Nullable, NSError * _Nullable))completionHandler __attribute__((swift_name("submitRequest(pool:requestJson:completionHandler:)")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("Ledger.ParseGetCredDefResponseCallbackResult")))
@interface Ssi_agentLedgerParseGetCredDefResponseCallbackResult : Ssi_agentBase <Ssi_agentCallbackData>
- (instancetype)initWithCommandHandle:(int32_t)commandHandle errorCode:(uint32_t)errorCode credDefId:(NSString *)credDefId credDefJson:(NSString *)credDefJson __attribute__((swift_name("init(commandHandle:errorCode:credDefId:credDefJson:)"))) __attribute__((objc_designated_initializer));
- (int32_t)component1 __attribute__((swift_name("component1()")));
- (uint32_t)component2 __attribute__((swift_name("component2()")));
- (NSString *)component3 __attribute__((swift_name("component3()")));
- (NSString *)component4 __attribute__((swift_name("component4()")));
- (Ssi_agentLedgerParseGetCredDefResponseCallbackResult *)doCopyCommandHandle:(int32_t)commandHandle errorCode:(uint32_t)errorCode credDefId:(NSString *)credDefId credDefJson:(NSString *)credDefJson __attribute__((swift_name("doCopy(commandHandle:errorCode:credDefId:credDefJson:)")));
- (BOOL)isEqual:(id _Nullable)other __attribute__((swift_name("isEqual(_:)")));
- (NSUInteger)hash __attribute__((swift_name("hash()")));
- (NSString *)description __attribute__((swift_name("description()")));
@property (readonly) int32_t commandHandle __attribute__((swift_name("commandHandle")));
@property (readonly) NSString *credDefId __attribute__((swift_name("credDefId")));
@property (readonly) NSString *credDefJson __attribute__((swift_name("credDefJson")));
@property (readonly) uint32_t errorCode __attribute__((swift_name("errorCode")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("Ledger.ParseGetRevocRegDefResponseCallbackResult")))
@interface Ssi_agentLedgerParseGetRevocRegDefResponseCallbackResult : Ssi_agentBase <Ssi_agentCallbackData>
- (instancetype)initWithCommandHandle:(int32_t)commandHandle errorCode:(uint32_t)errorCode revocRegDefId:(NSString *)revocRegDefId revocRegDefJson:(NSString *)revocRegDefJson __attribute__((swift_name("init(commandHandle:errorCode:revocRegDefId:revocRegDefJson:)"))) __attribute__((objc_designated_initializer));
- (int32_t)component1 __attribute__((swift_name("component1()")));
- (uint32_t)component2 __attribute__((swift_name("component2()")));
- (NSString *)component3 __attribute__((swift_name("component3()")));
- (NSString *)component4 __attribute__((swift_name("component4()")));
- (Ssi_agentLedgerParseGetRevocRegDefResponseCallbackResult *)doCopyCommandHandle:(int32_t)commandHandle errorCode:(uint32_t)errorCode revocRegDefId:(NSString *)revocRegDefId revocRegDefJson:(NSString *)revocRegDefJson __attribute__((swift_name("doCopy(commandHandle:errorCode:revocRegDefId:revocRegDefJson:)")));
- (BOOL)isEqual:(id _Nullable)other __attribute__((swift_name("isEqual(_:)")));
- (NSUInteger)hash __attribute__((swift_name("hash()")));
- (NSString *)description __attribute__((swift_name("description()")));
@property (readonly) int32_t commandHandle __attribute__((swift_name("commandHandle")));
@property (readonly) uint32_t errorCode __attribute__((swift_name("errorCode")));
@property (readonly) NSString *revocRegDefId __attribute__((swift_name("revocRegDefId")));
@property (readonly) NSString *revocRegDefJson __attribute__((swift_name("revocRegDefJson")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("Ledger.ParseGetRevocRegDeltaResponseCallbackResult")))
@interface Ssi_agentLedgerParseGetRevocRegDeltaResponseCallbackResult : Ssi_agentBase <Ssi_agentCallbackData>
- (instancetype)initWithCommandHandle:(int32_t)commandHandle errorCode:(uint32_t)errorCode revocRegDefId:(NSString *)revocRegDefId revocRegDeltaJson:(NSString *)revocRegDeltaJson timestamp:(uint64_t)timestamp __attribute__((swift_name("init(commandHandle:errorCode:revocRegDefId:revocRegDeltaJson:timestamp:)"))) __attribute__((objc_designated_initializer));
- (int32_t)component1 __attribute__((swift_name("component1()")));
- (uint32_t)component2 __attribute__((swift_name("component2()")));
- (NSString *)component3 __attribute__((swift_name("component3()")));
- (NSString *)component4 __attribute__((swift_name("component4()")));
- (uint64_t)component5 __attribute__((swift_name("component5()")));
- (Ssi_agentLedgerParseGetRevocRegDeltaResponseCallbackResult *)doCopyCommandHandle:(int32_t)commandHandle errorCode:(uint32_t)errorCode revocRegDefId:(NSString *)revocRegDefId revocRegDeltaJson:(NSString *)revocRegDeltaJson timestamp:(uint64_t)timestamp __attribute__((swift_name("doCopy(commandHandle:errorCode:revocRegDefId:revocRegDeltaJson:timestamp:)")));
- (BOOL)isEqual:(id _Nullable)other __attribute__((swift_name("isEqual(_:)")));
- (NSUInteger)hash __attribute__((swift_name("hash()")));
- (NSString *)description __attribute__((swift_name("description()")));
@property (readonly) int32_t commandHandle __attribute__((swift_name("commandHandle")));
@property (readonly) uint32_t errorCode __attribute__((swift_name("errorCode")));
@property (readonly) NSString *revocRegDefId __attribute__((swift_name("revocRegDefId")));
@property (readonly) NSString *revocRegDeltaJson __attribute__((swift_name("revocRegDeltaJson")));
@property (readonly) uint64_t timestamp __attribute__((swift_name("timestamp")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("Ledger.ParseGetSchemaResponseCallbackResult")))
@interface Ssi_agentLedgerParseGetSchemaResponseCallbackResult : Ssi_agentBase <Ssi_agentCallbackData>
- (instancetype)initWithCommandHandle:(int32_t)commandHandle errorCode:(uint32_t)errorCode schemaId:(NSString *)schemaId schemaJson:(NSString *)schemaJson __attribute__((swift_name("init(commandHandle:errorCode:schemaId:schemaJson:)"))) __attribute__((objc_designated_initializer));
- (int32_t)component1 __attribute__((swift_name("component1()")));
- (uint32_t)component2 __attribute__((swift_name("component2()")));
- (NSString *)component3 __attribute__((swift_name("component3()")));
- (NSString *)component4 __attribute__((swift_name("component4()")));
- (Ssi_agentLedgerParseGetSchemaResponseCallbackResult *)doCopyCommandHandle:(int32_t)commandHandle errorCode:(uint32_t)errorCode schemaId:(NSString *)schemaId schemaJson:(NSString *)schemaJson __attribute__((swift_name("doCopy(commandHandle:errorCode:schemaId:schemaJson:)")));
- (BOOL)isEqual:(id _Nullable)other __attribute__((swift_name("isEqual(_:)")));
- (NSUInteger)hash __attribute__((swift_name("hash()")));
- (NSString *)description __attribute__((swift_name("description()")));
@property (readonly) int32_t commandHandle __attribute__((swift_name("commandHandle")));
@property (readonly) uint32_t errorCode __attribute__((swift_name("errorCode")));
@property (readonly) NSString *schemaId __attribute__((swift_name("schemaId")));
@property (readonly) NSString *schemaJson __attribute__((swift_name("schemaJson")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("ParseRegistryResponseResult")))
@interface Ssi_agentParseRegistryResponseResult : Ssi_agentBase
- (instancetype)initWithTimestamp:(int64_t)timestamp objectJson:(NSString *)objectJson __attribute__((swift_name("init(timestamp:objectJson:)"))) __attribute__((objc_designated_initializer));
@property (readonly) NSString *objectJson __attribute__((swift_name("objectJson")));
@property (readonly) int64_t timestamp __attribute__((swift_name("timestamp")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("ParseResponseResult")))
@interface Ssi_agentParseResponseResult : Ssi_agentBase
- (instancetype)initWithObjectJson:(NSString *)objectJson __attribute__((swift_name("init(objectJson:)"))) __attribute__((objc_designated_initializer));
@property (readonly) NSString *objectJson __attribute__((swift_name("objectJson")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("Pool")))
@interface Ssi_agentPool : Ssi_agentBase
- (instancetype)initWithPoolHandle:(int32_t)poolHandle __attribute__((swift_name("init(poolHandle:)"))) __attribute__((objc_designated_initializer));
- (int32_t)getPoolHandle __attribute__((swift_name("getPoolHandle()")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("Pool.Companion")))
@interface Ssi_agentPoolCompanion : Ssi_agentBase
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
+ (instancetype)companion __attribute__((swift_name("init()")));

/**
 @note This method converts instances of CancellationException to errors.
 Other uncaught Kotlin exceptions are fatal.
*/
- (void)createPoolLedgerConfigConfigName:(NSString *)configName config:(NSString *)config completionHandler:(void (^)(Ssi_agentKotlinUnit * _Nullable, NSError * _Nullable))completionHandler __attribute__((swift_name("createPoolLedgerConfig(configName:config:completionHandler:)")));

/**
 @note This method converts instances of CancellationException to errors.
 Other uncaught Kotlin exceptions are fatal.
*/
- (void)openPoolLedgerConfigName:(NSString *)configName config:(NSString *)config completionHandler:(void (^)(Ssi_agentPool * _Nullable, NSError * _Nullable))completionHandler __attribute__((swift_name("openPoolLedger(configName:config:completionHandler:)")));

/**
 @note This method converts instances of CancellationException to errors.
 Other uncaught Kotlin exceptions are fatal.
*/
- (void)setProtocolVersionProtocolVersion:(int64_t)protocolVersion completionHandler:(void (^)(Ssi_agentKotlinUnit * _Nullable, NSError * _Nullable))completionHandler __attribute__((swift_name("setProtocolVersion(protocolVersion:completionHandler:)")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("PoolJSONParameters")))
@interface Ssi_agentPoolJSONParameters : Ssi_agentBase
- (instancetype)init __attribute__((swift_name("init()"))) __attribute__((objc_designated_initializer));
+ (instancetype)new __attribute__((availability(swift, unavailable, message="use object initializers instead")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("PoolJSONParameters.CreatePoolLedgerConfigJSONParameter")))
@interface Ssi_agentPoolJSONParametersCreatePoolLedgerConfigJSONParameter : Ssi_agentBase
- (instancetype)initWithGenesisTxn:(NSString *)genesisTxn __attribute__((swift_name("init(genesisTxn:)"))) __attribute__((objc_designated_initializer));
- (NSString *)component1 __attribute__((swift_name("component1()")));
- (Ssi_agentPoolJSONParametersCreatePoolLedgerConfigJSONParameter *)doCopyGenesisTxn:(NSString *)genesisTxn __attribute__((swift_name("doCopy(genesisTxn:)")));
- (BOOL)isEqual:(id _Nullable)other __attribute__((swift_name("isEqual(_:)")));
- (NSUInteger)hash __attribute__((swift_name("hash()")));
- (NSString *)description __attribute__((swift_name("description()")));
@property (readonly) NSString *genesisTxn __attribute__((swift_name("genesisTxn")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("PoolJSONParameters.CreatePoolLedgerConfigJSONParameterCompanion")))
@interface Ssi_agentPoolJSONParametersCreatePoolLedgerConfigJSONParameterCompanion : Ssi_agentBase
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
+ (instancetype)companion __attribute__((swift_name("init()")));
- (id<Ssi_agentKotlinx_serialization_coreKSerializer>)serializer __attribute__((swift_name("serializer()")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("PoolJSONParameters.OpenPoolLedgerJSONParameter")))
@interface Ssi_agentPoolJSONParametersOpenPoolLedgerJSONParameter : Ssi_agentBase
- (instancetype)initWithTimeout:(Ssi_agentInt * _Nullable)timeout extendedTimeout:(Ssi_agentInt * _Nullable)extendedTimeout __attribute__((swift_name("init(timeout:extendedTimeout:)"))) __attribute__((objc_designated_initializer));
- (Ssi_agentInt * _Nullable)component1 __attribute__((swift_name("component1()")));
- (Ssi_agentInt * _Nullable)component2 __attribute__((swift_name("component2()")));
- (Ssi_agentPoolJSONParametersOpenPoolLedgerJSONParameter *)doCopyTimeout:(Ssi_agentInt * _Nullable)timeout extendedTimeout:(Ssi_agentInt * _Nullable)extendedTimeout __attribute__((swift_name("doCopy(timeout:extendedTimeout:)")));
- (BOOL)isEqual:(id _Nullable)other __attribute__((swift_name("isEqual(_:)")));
- (NSUInteger)hash __attribute__((swift_name("hash()")));
- (NSString *)description __attribute__((swift_name("description()")));
@property (readonly) Ssi_agentInt * _Nullable extendedTimeout __attribute__((swift_name("extendedTimeout")));
@property (readonly) Ssi_agentInt * _Nullable timeout __attribute__((swift_name("timeout")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("PoolJSONParameters.OpenPoolLedgerJSONParameterCompanion")))
@interface Ssi_agentPoolJSONParametersOpenPoolLedgerJSONParameterCompanion : Ssi_agentBase
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
+ (instancetype)companion __attribute__((swift_name("init()")));
- (id<Ssi_agentKotlinx_serialization_coreKSerializer>)serializer __attribute__((swift_name("serializer()")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("GenesisGenerator")))
@interface Ssi_agentGenesisGenerator : Ssi_agentBase
- (instancetype)initWithIndyPoolIp:(NSString *)indyPoolIp dir:(NSString *)dir __attribute__((swift_name("init(indyPoolIp:dir:)"))) __attribute__((objc_designated_initializer));
- (NSString *)doInitGenesisFile __attribute__((swift_name("doInitGenesisFile()")));
@property (readonly) NSString *dir __attribute__((swift_name("dir")));
@property (readonly) NSString *indyPoolIp __attribute__((swift_name("indyPoolIp")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("PoolHelper")))
@interface Ssi_agentPoolHelper : Ssi_agentBase
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
+ (instancetype)poolHelper __attribute__((swift_name("init()")));

/**
 @note This method converts instances of CancellationException to errors.
 Other uncaught Kotlin exceptions are fatal.
*/
- (void)createNonExistingGenesisFile:(NSString *)genesisFile poolName:(NSString *)poolName completionHandler:(void (^)(Ssi_agentKotlinUnit * _Nullable, NSError * _Nullable))completionHandler __attribute__((swift_name("createNonExisting(genesisFile:poolName:completionHandler:)")));

/**
 @note This method converts instances of CancellationException to errors.
 Other uncaught Kotlin exceptions are fatal.
*/
- (void)createOrTruncGenesisFile:(NSString *)genesisFile poolName:(NSString *)poolName completionHandler:(void (^)(Ssi_agentKotlinUnit * _Nullable, NSError * _Nullable))completionHandler __attribute__((swift_name("createOrTrunc(genesisFile:poolName:completionHandler:)")));
- (BOOL)existsPoolName:(NSString *)poolName __attribute__((swift_name("exists(poolName:)")));

/**
 @note This method converts instances of CancellationException to errors.
 Other uncaught Kotlin exceptions are fatal.
*/
- (void)openExistingPoolName:(NSString *)poolName poolConfig:(Ssi_agentPoolJSONParametersOpenPoolLedgerJSONParameter *)poolConfig completionHandler:(void (^)(Ssi_agentPool * _Nullable, NSError * _Nullable))completionHandler __attribute__((swift_name("openExisting(poolName:poolConfig:completionHandler:)")));

/**
 @note This method converts instances of CancellationException to errors.
 Other uncaught Kotlin exceptions are fatal.
*/
- (void)openOrCreateGenesisFile:(NSString *)genesisFile poolName:(NSString *)poolName poolConfig:(Ssi_agentPoolJSONParametersOpenPoolLedgerJSONParameter *)poolConfig completionHandler:(void (^)(Ssi_agentPool * _Nullable, NSError * _Nullable))completionHandler __attribute__((swift_name("openOrCreate(genesisFile:poolName:poolConfig:completionHandler:)")));

/**
 @note This method converts instances of CancellationException to errors.
 Other uncaught Kotlin exceptions are fatal.
*/
- (void)openOrCreateFromFilenameFilename:(NSString *)filename completionHandler:(void (^)(Ssi_agentPool * _Nullable, NSError * _Nullable))completionHandler __attribute__((swift_name("openOrCreateFromFilename(filename:completionHandler:)")));

/**
 @note This method converts instances of CancellationException to errors.
 Other uncaught Kotlin exceptions are fatal.
*/
- (void)openOrCreateFromIpIpAddress:(NSString *)ipAddress dir:(NSString *)dir completionHandler:(void (^)(Ssi_agentPool * _Nullable, NSError * _Nullable))completionHandler __attribute__((swift_name("openOrCreateFromIp(ipAddress:dir:completionHandler:)")));
@property (readonly) NSString *DEFAULT_POOL_NAME __attribute__((swift_name("DEFAULT_POOL_NAME")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("TailsHelper")))
@interface Ssi_agentTailsHelper : Ssi_agentBase
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
+ (instancetype)tailsHelper __attribute__((swift_name("init()")));
- (int32_t)getTailsReaderHandlerTailsPath:(NSString *)tailsPath __attribute__((swift_name("getTailsReaderHandler(tailsPath:)")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("TailsConfig")))
@interface Ssi_agentTailsConfig : Ssi_agentBase
- (instancetype)initWithBaseDir:(NSString *)baseDir uriPattern:(NSString *)uriPattern __attribute__((swift_name("init(baseDir:uriPattern:)"))) __attribute__((objc_designated_initializer));
- (NSString *)component1 __attribute__((swift_name("component1()")));
- (NSString *)component2 __attribute__((swift_name("component2()")));
- (Ssi_agentTailsConfig *)doCopyBaseDir:(NSString *)baseDir uriPattern:(NSString *)uriPattern __attribute__((swift_name("doCopy(baseDir:uriPattern:)")));
- (BOOL)isEqual:(id _Nullable)other __attribute__((swift_name("isEqual(_:)")));
- (NSUInteger)hash __attribute__((swift_name("hash()")));
- (NSString *)description __attribute__((swift_name("description()")));
@property (readonly) NSString *baseDir __attribute__((swift_name("baseDir")));
@property (readonly) NSString *uriPattern __attribute__((swift_name("uriPattern")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("TailsConfig.Companion")))
@interface Ssi_agentTailsConfigCompanion : Ssi_agentBase
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
+ (instancetype)companion __attribute__((swift_name("init()")));
- (id<Ssi_agentKotlinx_serialization_coreKSerializer>)serializer __attribute__((swift_name("serializer()")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("TailsRequest")))
@interface Ssi_agentTailsRequest : Ssi_agentBase
- (instancetype)initWithTailsHash:(NSString *)tailsHash __attribute__((swift_name("init(tailsHash:)"))) __attribute__((objc_designated_initializer));
- (NSString *)component1 __attribute__((swift_name("component1()")));
- (Ssi_agentTailsRequest *)doCopyTailsHash:(NSString *)tailsHash __attribute__((swift_name("doCopy(tailsHash:)")));
- (BOOL)isEqual:(id _Nullable)other __attribute__((swift_name("isEqual(_:)")));
- (NSUInteger)hash __attribute__((swift_name("hash()")));
- (NSString *)description __attribute__((swift_name("description()")));
@property (readonly) NSString *tailsHash __attribute__((swift_name("tailsHash")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("TailsRequest.Companion")))
@interface Ssi_agentTailsRequestCompanion : Ssi_agentBase
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
+ (instancetype)companion __attribute__((swift_name("init()")));
- (id<Ssi_agentKotlinx_serialization_coreKSerializer>)serializer __attribute__((swift_name("serializer()")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("TailsResponse")))
@interface Ssi_agentTailsResponse : Ssi_agentBase
- (instancetype)initWithTailsHash:(NSString *)tailsHash tails:(NSDictionary<NSString *, Ssi_agentKotlinByteArray *> *)tails __attribute__((swift_name("init(tailsHash:tails:)"))) __attribute__((objc_designated_initializer));
- (NSString *)component1 __attribute__((swift_name("component1()")));
- (NSDictionary<NSString *, Ssi_agentKotlinByteArray *> *)component2 __attribute__((swift_name("component2()")));
- (Ssi_agentTailsResponse *)doCopyTailsHash:(NSString *)tailsHash tails:(NSDictionary<NSString *, Ssi_agentKotlinByteArray *> *)tails __attribute__((swift_name("doCopy(tailsHash:tails:)")));
- (BOOL)isEqual:(id _Nullable)other __attribute__((swift_name("isEqual(_:)")));
- (NSUInteger)hash __attribute__((swift_name("hash()")));
- (NSString *)description __attribute__((swift_name("description()")));
@property (readonly) NSDictionary<NSString *, Ssi_agentKotlinByteArray *> *tails __attribute__((swift_name("tails")));
@property (readonly) NSString *tailsHash __attribute__((swift_name("tailsHash")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("TailsResponse.Companion")))
@interface Ssi_agentTailsResponseCompanion : Ssi_agentBase
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
+ (instancetype)companion __attribute__((swift_name("init()")));
- (id<Ssi_agentKotlinx_serialization_coreKSerializer>)serializer __attribute__((swift_name("serializer()")));
@end;

__attribute__((swift_name("KotlinRuntimeException")))
@interface Ssi_agentKotlinRuntimeException : Ssi_agentKotlinException
- (instancetype)init __attribute__((swift_name("init()"))) __attribute__((objc_designated_initializer));
+ (instancetype)new __attribute__((availability(swift, unavailable, message="use object initializers instead")));
- (instancetype)initWithMessage:(NSString * _Nullable)message __attribute__((swift_name("init(message:)"))) __attribute__((objc_designated_initializer));
- (instancetype)initWithMessage:(NSString * _Nullable)message cause:(Ssi_agentKotlinThrowable * _Nullable)cause __attribute__((swift_name("init(message:cause:)"))) __attribute__((objc_designated_initializer));
- (instancetype)initWithCause:(Ssi_agentKotlinThrowable * _Nullable)cause __attribute__((swift_name("init(cause:)"))) __attribute__((objc_designated_initializer));
@end;

__attribute__((swift_name("KotlinIllegalArgumentException")))
@interface Ssi_agentKotlinIllegalArgumentException : Ssi_agentKotlinRuntimeException
- (instancetype)init __attribute__((swift_name("init()"))) __attribute__((objc_designated_initializer));
+ (instancetype)new __attribute__((availability(swift, unavailable, message="use object initializers instead")));
- (instancetype)initWithMessage:(NSString * _Nullable)message __attribute__((swift_name("init(message:)"))) __attribute__((objc_designated_initializer));
- (instancetype)initWithMessage:(NSString * _Nullable)message cause:(Ssi_agentKotlinThrowable * _Nullable)cause __attribute__((swift_name("init(message:cause:)"))) __attribute__((objc_designated_initializer));
- (instancetype)initWithCause:(Ssi_agentKotlinThrowable * _Nullable)cause __attribute__((swift_name("init(cause:)"))) __attribute__((objc_designated_initializer));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("IndyCredentialDefinitionAlreadyExistsException")))
@interface Ssi_agentIndyCredentialDefinitionAlreadyExistsException : Ssi_agentKotlinIllegalArgumentException
- (instancetype)initWithSchemaId:(id<Ssi_agentSchemaId>)schemaId msg:(NSString *)msg __attribute__((swift_name("init(schemaId:msg:)"))) __attribute__((objc_designated_initializer));
- (instancetype)init __attribute__((swift_name("init()"))) __attribute__((objc_designated_initializer)) __attribute__((unavailable));
+ (instancetype)new __attribute__((unavailable));
- (instancetype)initWithMessage:(NSString * _Nullable)message __attribute__((swift_name("init(message:)"))) __attribute__((objc_designated_initializer)) __attribute__((unavailable));
- (instancetype)initWithMessage:(NSString * _Nullable)message cause:(Ssi_agentKotlinThrowable * _Nullable)cause __attribute__((swift_name("init(message:cause:)"))) __attribute__((objc_designated_initializer)) __attribute__((unavailable));
- (instancetype)initWithCause:(Ssi_agentKotlinThrowable * _Nullable)cause __attribute__((swift_name("init(cause:)"))) __attribute__((objc_designated_initializer)) __attribute__((unavailable));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("IndyCredentialDefinitionNotFoundException")))
@interface Ssi_agentIndyCredentialDefinitionNotFoundException : Ssi_agentKotlinIllegalArgumentException
- (instancetype)initWithId:(id<Ssi_agentCredentialDefinitionId>)id msg:(NSString *)msg __attribute__((swift_name("init(id:msg:)"))) __attribute__((objc_designated_initializer));
- (instancetype)init __attribute__((swift_name("init()"))) __attribute__((objc_designated_initializer)) __attribute__((unavailable));
+ (instancetype)new __attribute__((unavailable));
- (instancetype)initWithMessage:(NSString * _Nullable)message __attribute__((swift_name("init(message:)"))) __attribute__((objc_designated_initializer)) __attribute__((unavailable));
- (instancetype)initWithMessage:(NSString * _Nullable)message cause:(Ssi_agentKotlinThrowable * _Nullable)cause __attribute__((swift_name("init(message:cause:)"))) __attribute__((objc_designated_initializer)) __attribute__((unavailable));
- (instancetype)initWithCause:(Ssi_agentKotlinThrowable * _Nullable)cause __attribute__((swift_name("init(cause:)"))) __attribute__((objc_designated_initializer)) __attribute__((unavailable));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("IndyCredentialMaximumReachedException")))
@interface Ssi_agentIndyCredentialMaximumReachedException : Ssi_agentKotlinIllegalArgumentException
- (instancetype)initWithRevRegId:(Ssi_agentRevocationRegistryDefinitionId *)revRegId __attribute__((swift_name("init(revRegId:)"))) __attribute__((objc_designated_initializer));
- (instancetype)init __attribute__((swift_name("init()"))) __attribute__((objc_designated_initializer)) __attribute__((unavailable));
+ (instancetype)new __attribute__((unavailable));
- (instancetype)initWithMessage:(NSString * _Nullable)message __attribute__((swift_name("init(message:)"))) __attribute__((objc_designated_initializer)) __attribute__((unavailable));
- (instancetype)initWithMessage:(NSString * _Nullable)message cause:(Ssi_agentKotlinThrowable * _Nullable)cause __attribute__((swift_name("init(message:cause:)"))) __attribute__((objc_designated_initializer)) __attribute__((unavailable));
- (instancetype)initWithCause:(Ssi_agentKotlinThrowable * _Nullable)cause __attribute__((swift_name("init(cause:)"))) __attribute__((objc_designated_initializer)) __attribute__((unavailable));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("IndyIssuer")))
@interface Ssi_agentIndyIssuer : Ssi_agentBase <Ssi_agentIssuer>
- (instancetype)initWithWalletHolder:(id<Ssi_agentWalletHolder>)walletHolder __attribute__((swift_name("init(walletHolder:)"))) __attribute__((objc_designated_initializer));
- (NSString *)signData:(NSString *)data __attribute__((swift_name("sign(data:)")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("IndyProver")))
@interface Ssi_agentIndyProver : Ssi_agentBase <Ssi_agentProver>
- (instancetype)initWithWalletHolder:(id<Ssi_agentWalletHolder>)walletHolder __attribute__((swift_name("init(walletHolder:)"))) __attribute__((objc_designated_initializer));
- (id<Ssi_agentCredential>)buildCredentialObjectFromRawDataData:(Ssi_agentData *)data __attribute__((swift_name("buildCredentialObjectFromRawData(data:)")));
- (id<Ssi_agentCredentialOffer>)buildCredentialOfferObjectFromRawDataData:(Ssi_agentData *)data __attribute__((swift_name("buildCredentialOfferObjectFromRawData(data:)")));
- (id<Ssi_agentPresentationRequest>)buildPresentationRequestObjectFromRawDataData:(Ssi_agentData *)data __attribute__((swift_name("buildPresentationRequestObjectFromRawData(data:)")));
- (id<Ssi_agentCredentialDefinitionId>)createCredentialDefinitionIdFromOfferCredentialOffer:(id<Ssi_agentCredentialOffer>)credentialOffer __attribute__((swift_name("createCredentialDefinitionIdFromOffer(credentialOffer:)")));

/**
 @note This method converts instances of CancellationException to errors.
 Other uncaught Kotlin exceptions are fatal.
*/
- (void)createCredentialRequestProverDid:(NSString *)proverDid credentialDefinition:(id<Ssi_agentCredentialDefinition>)credentialDefinition credentialOffer:(id<Ssi_agentCredentialOffer>)credentialOffer masterSecretId:(NSString *)masterSecretId completionHandler:(void (^)(Ssi_agentCredentialRequestInfo * _Nullable, NSError * _Nullable))completionHandler __attribute__((swift_name("createCredentialRequest(proverDid:credentialDefinition:credentialOffer:masterSecretId:completionHandler:)")));

/**
 @note This method converts instances of CancellationException to errors.
 Other uncaught Kotlin exceptions are fatal.
*/
- (void)createMasterSecretId:(NSString *)id completionHandler:(void (^)(Ssi_agentKotlinUnit * _Nullable, NSError * _Nullable))completionHandler __attribute__((swift_name("createMasterSecret(id:completionHandler:)")));

/**
 @note This method converts instances of CancellationException to errors.
 Other uncaught Kotlin exceptions are fatal.
*/
- (void)createPresentationPresentationRequest:(id<Ssi_agentPresentationRequest>)presentationRequest ledgerConnector:(id<Ssi_agentLedgerConnector>)ledgerConnector completionHandler:(void (^)(id<Ssi_agentPresentation> _Nullable, NSError * _Nullable))completionHandler __attribute__((swift_name("createPresentation(presentationRequest:ledgerConnector:completionHandler:)")));

/**
 @note This method converts instances of CancellationException to errors.
 Other uncaught Kotlin exceptions are fatal.
*/
- (void)createRevocationStateRevocationRegistryDefinition:(id<Ssi_agentRevocationRegistryDefinition>)revocationRegistryDefinition revocationRegistryEntry:(Ssi_agentRevocationRegistryEntry *)revocationRegistryEntry credentialRevocationId:(NSString *)credentialRevocationId timestamp:(int64_t)timestamp completionHandler:(void (^)(Ssi_agentRevocationState * _Nullable, NSError * _Nullable))completionHandler __attribute__((swift_name("createRevocationState(revocationRegistryDefinition:revocationRegistryEntry:credentialRevocationId:timestamp:completionHandler:)")));
- (Ssi_agentData *)extractCredentialRequestDataFromCredentialInfoCredentialRequestInfo:(Ssi_agentCredentialRequestInfo *)credentialRequestInfo __attribute__((swift_name("extractCredentialRequestDataFromCredentialInfo(credentialRequestInfo:)")));
- (Ssi_agentData *)extractPresentationDataFromPresentationPresentation:(id<Ssi_agentPresentation>)presentation __attribute__((swift_name("extractPresentationDataFromPresentation(presentation:)")));

/**
 @note This method converts instances of CancellationException to errors.
 Other uncaught Kotlin exceptions are fatal.
*/
- (void)getCredentialExchangeRecordByThreadThread:(Ssi_agentThread *)thread completionHandler:(void (^)(Ssi_agentCredentialExchangeRecord * _Nullable, NSError * _Nullable))completionHandler __attribute__((swift_name("getCredentialExchangeRecordByThread(thread:completionHandler:)")));

/**
 @note This method converts instances of CancellationException to errors.
 Other uncaught Kotlin exceptions are fatal.
*/
- (void)receiveCredentialCredential:(id<Ssi_agentCredential>)credential credentialRequestInfo:(Ssi_agentCredentialRequestInfo *)credentialRequestInfo credentialDefinition:(id<Ssi_agentCredentialDefinition>)credentialDefinition revocationRegistryDefinition:(id<Ssi_agentRevocationRegistryDefinition> _Nullable)revocationRegistryDefinition completionHandler:(void (^)(NSString * _Nullable, NSError * _Nullable))completionHandler __attribute__((swift_name("receiveCredential(credential:credentialRequestInfo:credentialDefinition:revocationRegistryDefinition:completionHandler:)")));

/**
 @note This method converts instances of CancellationException to errors.
 Other uncaught Kotlin exceptions are fatal.
*/
- (void)removeCredentialExchangeRecordByThreadThread:(Ssi_agentThread *)thread completionHandler:(void (^)(Ssi_agentKotlinUnit * _Nullable, NSError * _Nullable))completionHandler __attribute__((swift_name("removeCredentialExchangeRecordByThread(thread:completionHandler:)")));

/**
 @note This method converts instances of CancellationException to errors.
 Other uncaught Kotlin exceptions are fatal.
*/
- (void)storeCredentialExchangeRecordCredentialExchangeRecord:(Ssi_agentCredentialExchangeRecord *)credentialExchangeRecord completionHandler:(void (^)(Ssi_agentKotlinUnit * _Nullable, NSError * _Nullable))completionHandler __attribute__((swift_name("storeCredentialExchangeRecord(credentialExchangeRecord:completionHandler:)")));
@property (readonly) id<Ssi_agentWalletHolder> walletHolder __attribute__((swift_name("walletHolder")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("IndyRevDeltaNotFoundException")))
@interface Ssi_agentIndyRevDeltaNotFoundException : Ssi_agentKotlinIllegalArgumentException
- (instancetype)initWithId:(Ssi_agentRevocationRegistryDefinitionId *)id msg:(NSString *)msg __attribute__((swift_name("init(id:msg:)"))) __attribute__((objc_designated_initializer));
- (instancetype)init __attribute__((swift_name("init()"))) __attribute__((objc_designated_initializer)) __attribute__((unavailable));
+ (instancetype)new __attribute__((unavailable));
- (instancetype)initWithMessage:(NSString * _Nullable)message __attribute__((swift_name("init(message:)"))) __attribute__((objc_designated_initializer)) __attribute__((unavailable));
- (instancetype)initWithMessage:(NSString * _Nullable)message cause:(Ssi_agentKotlinThrowable * _Nullable)cause __attribute__((swift_name("init(message:cause:)"))) __attribute__((objc_designated_initializer)) __attribute__((unavailable));
- (instancetype)initWithCause:(Ssi_agentKotlinThrowable * _Nullable)cause __attribute__((swift_name("init(cause:)"))) __attribute__((objc_designated_initializer)) __attribute__((unavailable));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("IndyRevRegNotFoundException")))
@interface Ssi_agentIndyRevRegNotFoundException : Ssi_agentKotlinIllegalArgumentException
- (instancetype)initWithId:(Ssi_agentRevocationRegistryDefinitionId *)id msg:(NSString *)msg __attribute__((swift_name("init(id:msg:)"))) __attribute__((objc_designated_initializer));
- (instancetype)init __attribute__((swift_name("init()"))) __attribute__((objc_designated_initializer)) __attribute__((unavailable));
+ (instancetype)new __attribute__((unavailable));
- (instancetype)initWithMessage:(NSString * _Nullable)message __attribute__((swift_name("init(message:)"))) __attribute__((objc_designated_initializer)) __attribute__((unavailable));
- (instancetype)initWithMessage:(NSString * _Nullable)message cause:(Ssi_agentKotlinThrowable * _Nullable)cause __attribute__((swift_name("init(message:cause:)"))) __attribute__((objc_designated_initializer)) __attribute__((unavailable));
- (instancetype)initWithCause:(Ssi_agentKotlinThrowable * _Nullable)cause __attribute__((swift_name("init(cause:)"))) __attribute__((objc_designated_initializer)) __attribute__((unavailable));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("IndySchemaAlreadyExistsException")))
@interface Ssi_agentIndySchemaAlreadyExistsException : Ssi_agentKotlinIllegalArgumentException
- (instancetype)initWithName:(NSString *)name version:(NSString *)version __attribute__((swift_name("init(name:version:)"))) __attribute__((objc_designated_initializer));
- (instancetype)init __attribute__((swift_name("init()"))) __attribute__((objc_designated_initializer)) __attribute__((unavailable));
+ (instancetype)new __attribute__((unavailable));
- (instancetype)initWithMessage:(NSString * _Nullable)message __attribute__((swift_name("init(message:)"))) __attribute__((objc_designated_initializer)) __attribute__((unavailable));
- (instancetype)initWithMessage:(NSString * _Nullable)message cause:(Ssi_agentKotlinThrowable * _Nullable)cause __attribute__((swift_name("init(message:cause:)"))) __attribute__((objc_designated_initializer)) __attribute__((unavailable));
- (instancetype)initWithCause:(Ssi_agentKotlinThrowable * _Nullable)cause __attribute__((swift_name("init(cause:)"))) __attribute__((objc_designated_initializer)) __attribute__((unavailable));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("IndySchemaNotFoundException")))
@interface Ssi_agentIndySchemaNotFoundException : Ssi_agentKotlinIllegalArgumentException
- (instancetype)initWithId:(id<Ssi_agentSchemaId>)id msg:(NSString *)msg __attribute__((swift_name("init(id:msg:)"))) __attribute__((objc_designated_initializer));
- (instancetype)init __attribute__((swift_name("init()"))) __attribute__((objc_designated_initializer)) __attribute__((unavailable));
+ (instancetype)new __attribute__((unavailable));
- (instancetype)initWithMessage:(NSString * _Nullable)message __attribute__((swift_name("init(message:)"))) __attribute__((objc_designated_initializer)) __attribute__((unavailable));
- (instancetype)initWithMessage:(NSString * _Nullable)message cause:(Ssi_agentKotlinThrowable * _Nullable)cause __attribute__((swift_name("init(message:cause:)"))) __attribute__((objc_designated_initializer)) __attribute__((unavailable));
- (instancetype)initWithCause:(Ssi_agentKotlinThrowable * _Nullable)cause __attribute__((swift_name("init(cause:)"))) __attribute__((objc_designated_initializer)) __attribute__((unavailable));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("IndyTrustee")))
@interface Ssi_agentIndyTrustee : Ssi_agentBase <Ssi_agentTrustee>
- (instancetype)initWithWalletHolder:(id<Ssi_agentWalletHolder>)walletHolder __attribute__((swift_name("init(walletHolder:)"))) __attribute__((objc_designated_initializer));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("IndyVerifier")))
@interface Ssi_agentIndyVerifier : Ssi_agentBase <Ssi_agentVerifier>
- (instancetype)initWithWalletHolder:(id<Ssi_agentWalletHolder>)walletHolder __attribute__((swift_name("init(walletHolder:)"))) __attribute__((objc_designated_initializer));
@end;

__attribute__((swift_name("IndyWalletHolder")))
@interface Ssi_agentIndyWalletHolder : Ssi_agentBase <Ssi_agentWalletHolder>
- (instancetype)init __attribute__((swift_name("init()"))) __attribute__((objc_designated_initializer));
+ (instancetype)new __attribute__((availability(swift, unavailable, message="use object initializers instead")));
- (NSString *)createSessionDidIdentityRecord:(Ssi_agentIdentityDetails *)identityRecord __attribute__((swift_name("createSessionDid(identityRecord:)")));

/**
 @note This method converts instances of CancellationException to errors.
 Other uncaught Kotlin exceptions are fatal.
*/
- (void)findConnectionByVerKeyVerKey:(NSString *)verKey completionHandler:(void (^)(Ssi_agentConnection * _Nullable, NSError * _Nullable))completionHandler __attribute__((swift_name("findConnectionByVerKey(verKey:completionHandler:)")));

/**
 @note This method converts instances of CancellationException to errors.
 Other uncaught Kotlin exceptions are fatal.
*/
- (void)getConnectionRecordByIdConnectionId:(NSString *)connectionId completionHandler:(void (^)(Ssi_agentConnection * _Nullable, NSError * _Nullable))completionHandler __attribute__((swift_name("getConnectionRecordById(connectionId:completionHandler:)")));
- (Ssi_agentIdentityDetails *)getIdentityDetails __attribute__((swift_name("getIdentityDetails()")));
- (Ssi_agentIdentityDetails *)getIdentityDetailsDid:(NSString *)did __attribute__((swift_name("getIdentityDetails(did:)")));
- (NSString *)getTailsPath __attribute__((swift_name("getTailsPath()")));
- (id)getWallet __attribute__((swift_name("getWallet()")));

/**
 @note This method converts instances of CancellationException to errors.
 Other uncaught Kotlin exceptions are fatal.
*/
- (void)openOrCreateWalletWithCompletionHandler:(void (^)(Ssi_agentKotlinUnit * _Nullable, NSError * _Nullable))completionHandler __attribute__((swift_name("openOrCreateWallet(completionHandler:)")));

/**
 @note This method converts instances of CancellationException to errors.
 Other uncaught Kotlin exceptions are fatal.
*/
- (void)packMessageMessage:(Ssi_agentMessage *)message recipientKeys:(NSArray<NSString *> *)recipientKeys useAnonCrypt:(BOOL)useAnonCrypt completionHandler:(void (^)(NSString * _Nullable, NSError * _Nullable))completionHandler __attribute__((swift_name("packMessage(message:recipientKeys:useAnonCrypt:completionHandler:)")));

/**
 @note This method converts instances of CancellationException to errors.
 Other uncaught Kotlin exceptions are fatal.
*/
- (void)storeConnectionRecordConnection:(Ssi_agentConnection *)connection completionHandler:(void (^)(Ssi_agentKotlinUnit * _Nullable, NSError * _Nullable))completionHandler __attribute__((swift_name("storeConnectionRecord(connection:completionHandler:)")));

/**
 @note This method converts instances of CancellationException to errors.
 Other uncaught Kotlin exceptions are fatal.
*/
- (void)unPackMessagePackedMessage:(Ssi_agentMessage *)packedMessage completionHandler:(void (^)(Ssi_agentMessage * _Nullable, NSError * _Nullable))completionHandler __attribute__((swift_name("unPackMessage(packedMessage:completionHandler:)")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("ObjectHolder")))
@interface Ssi_agentObjectHolder<T> : Ssi_agentBase
- (instancetype)initWithObj:(T _Nullable)obj __attribute__((swift_name("init(obj:)"))) __attribute__((objc_designated_initializer));
- (T _Nullable)component1 __attribute__((swift_name("component1()")));
- (Ssi_agentObjectHolder<T> *)doCopyObj:(T _Nullable)obj __attribute__((swift_name("doCopy(obj:)")));
- (BOOL)isEqual:(id _Nullable)other __attribute__((swift_name("isEqual(_:)")));
- (NSUInteger)hash __attribute__((swift_name("hash()")));
- (NSString *)description __attribute__((swift_name("description()")));
@property T _Nullable obj __attribute__((swift_name("obj")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("CommonWalletRecord")))
@interface Ssi_agentCommonWalletRecord : Ssi_agentBase
- (instancetype)initWithType:(Ssi_agentWalletRecordType *)type id:(NSString *)id value:(NSString *)value tags:(NSString * _Nullable)tags __attribute__((swift_name("init(type:id:value:tags:)"))) __attribute__((objc_designated_initializer));
- (Ssi_agentWalletRecordType *)component1 __attribute__((swift_name("component1()")));
- (NSString *)component2 __attribute__((swift_name("component2()")));
- (NSString *)component3 __attribute__((swift_name("component3()")));
- (NSString * _Nullable)component4 __attribute__((swift_name("component4()")));
- (Ssi_agentCommonWalletRecord *)doCopyType:(Ssi_agentWalletRecordType *)type id:(NSString *)id value:(NSString *)value tags:(NSString * _Nullable)tags __attribute__((swift_name("doCopy(type:id:value:tags:)")));
- (BOOL)isEqual:(id _Nullable)other __attribute__((swift_name("isEqual(_:)")));
- (NSUInteger)hash __attribute__((swift_name("hash()")));
- (NSString *)description __attribute__((swift_name("description()")));
@property (readonly) NSString *id __attribute__((swift_name("id")));
@property (readonly) NSString * _Nullable tags __attribute__((swift_name("tags")));
@property (readonly) Ssi_agentWalletRecordType *type __attribute__((swift_name("type")));
@property (readonly) NSString *value __attribute__((swift_name("value")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("CommonWalletRecord.Companion")))
@interface Ssi_agentCommonWalletRecordCompanion : Ssi_agentBase
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
+ (instancetype)companion __attribute__((swift_name("init()")));
- (id<Ssi_agentKotlinx_serialization_coreKSerializer>)serializer __attribute__((swift_name("serializer()")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("RetrievedWalletRecords")))
@interface Ssi_agentRetrievedWalletRecords : Ssi_agentBase
- (instancetype)initWithTotalCount:(Ssi_agentInt * _Nullable)totalCount records:(NSArray<Ssi_agentCommonWalletRecord *> * _Nullable)records __attribute__((swift_name("init(totalCount:records:)"))) __attribute__((objc_designated_initializer));
- (Ssi_agentInt * _Nullable)component1 __attribute__((swift_name("component1()")));
- (NSArray<Ssi_agentCommonWalletRecord *> * _Nullable)component2 __attribute__((swift_name("component2()")));
- (Ssi_agentRetrievedWalletRecords *)doCopyTotalCount:(Ssi_agentInt * _Nullable)totalCount records:(NSArray<Ssi_agentCommonWalletRecord *> * _Nullable)records __attribute__((swift_name("doCopy(totalCount:records:)")));
- (BOOL)isEqual:(id _Nullable)other __attribute__((swift_name("isEqual(_:)")));
- (NSUInteger)hash __attribute__((swift_name("hash()")));
- (NSString *)description __attribute__((swift_name("description()")));
@property (readonly) NSArray<Ssi_agentCommonWalletRecord *> * _Nullable records __attribute__((swift_name("records")));
@property (readonly) Ssi_agentInt * _Nullable totalCount __attribute__((swift_name("totalCount")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("RetrievedWalletRecords.Companion")))
@interface Ssi_agentRetrievedWalletRecordsCompanion : Ssi_agentBase
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
+ (instancetype)companion __attribute__((swift_name("init()")));
- (id<Ssi_agentKotlinx_serialization_coreKSerializer>)serializer __attribute__((swift_name("serializer()")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("StorageConfig")))
@interface Ssi_agentStorageConfig : Ssi_agentBase
- (instancetype)initWithPath:(NSString *)path __attribute__((swift_name("init(path:)"))) __attribute__((objc_designated_initializer));
- (NSString *)component1 __attribute__((swift_name("component1()")));
- (Ssi_agentStorageConfig *)doCopyPath:(NSString *)path __attribute__((swift_name("doCopy(path:)")));
- (BOOL)isEqual:(id _Nullable)other __attribute__((swift_name("isEqual(_:)")));
- (NSUInteger)hash __attribute__((swift_name("hash()")));
- (NSString *)description __attribute__((swift_name("description()")));
@property (readonly) NSString *path __attribute__((swift_name("path")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("StorageConfig.Companion")))
@interface Ssi_agentStorageConfigCompanion : Ssi_agentBase
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
+ (instancetype)companion __attribute__((swift_name("init()")));
- (id<Ssi_agentKotlinx_serialization_coreKSerializer>)serializer __attribute__((swift_name("serializer()")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("WalletConfig")))
@interface Ssi_agentWalletConfig : Ssi_agentBase
- (instancetype)initWithId:(NSString *)id storageType:(NSString *)storageType storageConfig:(Ssi_agentStorageConfig * _Nullable)storageConfig __attribute__((swift_name("init(id:storageType:storageConfig:)"))) __attribute__((objc_designated_initializer));
- (NSString *)component1 __attribute__((swift_name("component1()")));
- (NSString *)component2 __attribute__((swift_name("component2()")));
- (Ssi_agentStorageConfig * _Nullable)component3 __attribute__((swift_name("component3()")));
- (Ssi_agentWalletConfig *)doCopyId:(NSString *)id storageType:(NSString *)storageType storageConfig:(Ssi_agentStorageConfig * _Nullable)storageConfig __attribute__((swift_name("doCopy(id:storageType:storageConfig:)")));
- (BOOL)isEqual:(id _Nullable)other __attribute__((swift_name("isEqual(_:)")));
- (NSUInteger)hash __attribute__((swift_name("hash()")));
- (NSString *)description __attribute__((swift_name("description()")));
@property (readonly) NSString *id __attribute__((swift_name("id")));
@property (readonly) Ssi_agentStorageConfig * _Nullable storageConfig __attribute__((swift_name("storageConfig")));
@property (readonly) NSString *storageType __attribute__((swift_name("storageType")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("WalletConfig.Companion")))
@interface Ssi_agentWalletConfigCompanion : Ssi_agentBase
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
+ (instancetype)companion __attribute__((swift_name("init()")));
- (id<Ssi_agentKotlinx_serialization_coreKSerializer>)serializer __attribute__((swift_name("serializer()")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("WalletPassword")))
@interface Ssi_agentWalletPassword : Ssi_agentBase
- (instancetype)initWithKey:(NSString *)key __attribute__((swift_name("init(key:)"))) __attribute__((objc_designated_initializer));
- (NSString *)component1 __attribute__((swift_name("component1()")));
- (Ssi_agentWalletPassword *)doCopyKey:(NSString *)key __attribute__((swift_name("doCopy(key:)")));
- (BOOL)isEqual:(id _Nullable)other __attribute__((swift_name("isEqual(_:)")));
- (NSUInteger)hash __attribute__((swift_name("hash()")));
- (NSString *)description __attribute__((swift_name("description()")));
@property (readonly) NSString *key __attribute__((swift_name("key")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("WalletPassword.Companion")))
@interface Ssi_agentWalletPasswordCompanion : Ssi_agentBase
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
+ (instancetype)companion __attribute__((swift_name("init()")));
- (id<Ssi_agentKotlinx_serialization_coreKSerializer>)serializer __attribute__((swift_name("serializer()")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("WalletRecordTag")))
@interface Ssi_agentWalletRecordTag : Ssi_agentKotlinEnum<Ssi_agentWalletRecordTag *>
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
- (instancetype)initWithName:(NSString *)name ordinal:(int32_t)ordinal __attribute__((swift_name("init(name:ordinal:)"))) __attribute__((objc_designated_initializer)) __attribute__((unavailable));
@property (class, readonly) Ssi_agentWalletRecordTag *connectionverkey __attribute__((swift_name("connectionverkey")));
+ (Ssi_agentKotlinArray<Ssi_agentWalletRecordTag *> *)values __attribute__((swift_name("values()")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("WalletRecordType")))
@interface Ssi_agentWalletRecordType : Ssi_agentKotlinEnum<Ssi_agentWalletRecordType *>
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
- (instancetype)initWithName:(NSString *)name ordinal:(int32_t)ordinal __attribute__((swift_name("init(name:ordinal:)"))) __attribute__((objc_designated_initializer)) __attribute__((unavailable));
@property (class, readonly) Ssi_agentWalletRecordType *connectionrecord __attribute__((swift_name("connectionrecord")));
@property (class, readonly) Ssi_agentWalletRecordType *credentialexchangerecord __attribute__((swift_name("credentialexchangerecord")));
+ (Ssi_agentKotlinArray<Ssi_agentWalletRecordType *> *)values __attribute__((swift_name("values()")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("WalletRecordType.Companion")))
@interface Ssi_agentWalletRecordTypeCompanion : Ssi_agentBase
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
+ (instancetype)companion __attribute__((swift_name("init()")));
- (id<Ssi_agentKotlinx_serialization_coreKSerializer>)serializer __attribute__((swift_name("serializer()")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("CredentialAttributeReference")))
@interface Ssi_agentCredentialAttributeReference : Ssi_agentBase
- (instancetype)initWithName:(NSString *)name restrictions:(NSArray<Ssi_agentRestriction *> *)restrictions __attribute__((swift_name("init(name:restrictions:)"))) __attribute__((objc_designated_initializer));
- (NSString *)component1 __attribute__((swift_name("component1()")));
- (NSArray<Ssi_agentRestriction *> *)component2 __attribute__((swift_name("component2()")));
- (Ssi_agentCredentialAttributeReference *)doCopyName:(NSString *)name restrictions:(NSArray<Ssi_agentRestriction *> *)restrictions __attribute__((swift_name("doCopy(name:restrictions:)")));
- (BOOL)isEqual:(id _Nullable)other __attribute__((swift_name("isEqual(_:)")));
- (NSUInteger)hash __attribute__((swift_name("hash()")));
- (NSString *)description __attribute__((swift_name("description()")));
@property (readonly) NSString *name __attribute__((swift_name("name")));
@property (readonly) NSArray<Ssi_agentRestriction *> *restrictions __attribute__((swift_name("restrictions")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("CredentialAttributeReference.Companion")))
@interface Ssi_agentCredentialAttributeReferenceCompanion : Ssi_agentBase
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
+ (instancetype)companion __attribute__((swift_name("init()")));
- (id<Ssi_agentKotlinx_serialization_coreKSerializer>)serializer __attribute__((swift_name("serializer()")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("CredentialPredicateReference")))
@interface Ssi_agentCredentialPredicateReference : Ssi_agentBase
- (instancetype)initWithName:(NSString *)name pValue:(int32_t)pValue pType:(NSString *)pType restrictions:(NSArray<Ssi_agentRestriction *> *)restrictions __attribute__((swift_name("init(name:pValue:pType:restrictions:)"))) __attribute__((objc_designated_initializer));
- (NSString *)component1 __attribute__((swift_name("component1()")));
- (int32_t)component2 __attribute__((swift_name("component2()")));
- (NSString *)component3 __attribute__((swift_name("component3()")));
- (NSArray<Ssi_agentRestriction *> *)component4 __attribute__((swift_name("component4()")));
- (Ssi_agentCredentialPredicateReference *)doCopyName:(NSString *)name pValue:(int32_t)pValue pType:(NSString *)pType restrictions:(NSArray<Ssi_agentRestriction *> *)restrictions __attribute__((swift_name("doCopy(name:pValue:pType:restrictions:)")));
- (BOOL)isEqual:(id _Nullable)other __attribute__((swift_name("isEqual(_:)")));
- (NSUInteger)hash __attribute__((swift_name("hash()")));
- (NSString *)description __attribute__((swift_name("description()")));
@property (readonly) NSString *name __attribute__((swift_name("name")));
@property (readonly) NSString *pType __attribute__((swift_name("pType")));
@property (readonly) int32_t pValue __attribute__((swift_name("pValue")));
@property (readonly) NSArray<Ssi_agentRestriction *> *restrictions __attribute__((swift_name("restrictions")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("CredentialPredicateReference.Companion")))
@interface Ssi_agentCredentialPredicateReferenceCompanion : Ssi_agentBase
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
+ (instancetype)companion __attribute__((swift_name("init()")));
- (id<Ssi_agentKotlinx_serialization_coreKSerializer>)serializer __attribute__((swift_name("serializer()")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("CredentialReference")))
@interface Ssi_agentCredentialReference : Ssi_agentBase
- (instancetype)initWithSchemaIdRaw:(NSString *)schemaIdRaw credentialDefinitionIdRaw:(NSString *)credentialDefinitionIdRaw referent:(NSString *)referent attributes:(NSString *)attributes credentialRevocationId:(NSString * _Nullable)credentialRevocationId revocationRegistryIdRaw:(NSString * _Nullable)revocationRegistryIdRaw __attribute__((swift_name("init(schemaIdRaw:credentialDefinitionIdRaw:referent:attributes:credentialRevocationId:revocationRegistryIdRaw:)"))) __attribute__((objc_designated_initializer));
- (NSString *)component1 __attribute__((swift_name("component1()")));
- (NSString *)component2 __attribute__((swift_name("component2()")));
- (NSString *)component3 __attribute__((swift_name("component3()")));
- (NSString *)component4 __attribute__((swift_name("component4()")));
- (NSString * _Nullable)component5 __attribute__((swift_name("component5()")));
- (NSString * _Nullable)component6 __attribute__((swift_name("component6()")));
- (Ssi_agentCredentialReference *)doCopySchemaIdRaw:(NSString *)schemaIdRaw credentialDefinitionIdRaw:(NSString *)credentialDefinitionIdRaw referent:(NSString *)referent attributes:(NSString *)attributes credentialRevocationId:(NSString * _Nullable)credentialRevocationId revocationRegistryIdRaw:(NSString * _Nullable)revocationRegistryIdRaw __attribute__((swift_name("doCopy(schemaIdRaw:credentialDefinitionIdRaw:referent:attributes:credentialRevocationId:revocationRegistryIdRaw:)")));
- (BOOL)isEqual:(id _Nullable)other __attribute__((swift_name("isEqual(_:)")));
- (NSUInteger)hash __attribute__((swift_name("hash()")));
- (NSString *)description __attribute__((swift_name("description()")));
@property (readonly) NSString *attributes __attribute__((swift_name("attributes")));
@property (readonly) NSString *credentialDefinitionIdRaw __attribute__((swift_name("credentialDefinitionIdRaw")));
@property (readonly) NSString * _Nullable credentialRevocationId __attribute__((swift_name("credentialRevocationId")));
@property (readonly) NSString *referent __attribute__((swift_name("referent")));
@property (readonly) NSString * _Nullable revocationRegistryIdRaw __attribute__((swift_name("revocationRegistryIdRaw")));
@property (readonly) NSString *schemaIdRaw __attribute__((swift_name("schemaIdRaw")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("CredentialReference.Companion")))
@interface Ssi_agentCredentialReferenceCompanion : Ssi_agentBase
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
+ (instancetype)companion __attribute__((swift_name("init()")));
- (id<Ssi_agentKotlinx_serialization_coreKSerializer>)serializer __attribute__((swift_name("serializer()")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("IndyCredInfo")))
@interface Ssi_agentIndyCredInfo : Ssi_agentBase
- (instancetype)initWithReferent:(NSString *)referent attrs:(NSDictionary<NSString *, NSString *> *)attrs schemaId:(NSString *)schemaId credDefId:(NSString *)credDefId revRegId:(NSString * _Nullable)revRegId credRevId:(NSString * _Nullable)credRevId __attribute__((swift_name("init(referent:attrs:schemaId:credDefId:revRegId:credRevId:)"))) __attribute__((objc_designated_initializer));
- (NSString *)component1 __attribute__((swift_name("component1()")));
- (NSDictionary<NSString *, NSString *> *)component2 __attribute__((swift_name("component2()")));
- (NSString *)component3 __attribute__((swift_name("component3()")));
- (NSString *)component4 __attribute__((swift_name("component4()")));
- (NSString * _Nullable)component5 __attribute__((swift_name("component5()")));
- (NSString * _Nullable)component6 __attribute__((swift_name("component6()")));
- (Ssi_agentIndyCredInfo *)doCopyReferent:(NSString *)referent attrs:(NSDictionary<NSString *, NSString *> *)attrs schemaId:(NSString *)schemaId credDefId:(NSString *)credDefId revRegId:(NSString * _Nullable)revRegId credRevId:(NSString * _Nullable)credRevId __attribute__((swift_name("doCopy(referent:attrs:schemaId:credDefId:revRegId:credRevId:)")));
- (BOOL)isEqual:(id _Nullable)other __attribute__((swift_name("isEqual(_:)")));
- (NSUInteger)hash __attribute__((swift_name("hash()")));
- (NSString *)description __attribute__((swift_name("description()")));
@property (readonly) NSDictionary<NSString *, NSString *> *attrs __attribute__((swift_name("attrs")));
@property (readonly) NSString *credDefId __attribute__((swift_name("credDefId")));
@property (readonly) NSString * _Nullable credRevId __attribute__((swift_name("credRevId")));
@property (readonly) NSString *referent __attribute__((swift_name("referent")));
@property (readonly) NSString * _Nullable revRegId __attribute__((swift_name("revRegId")));
@property (readonly) NSString *schemaId __attribute__((swift_name("schemaId")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("IndyCredInfo.Companion")))
@interface Ssi_agentIndyCredInfoCompanion : Ssi_agentBase
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
+ (instancetype)companion __attribute__((swift_name("init()")));
- (id<Ssi_agentKotlinx_serialization_coreKSerializer>)serializer __attribute__((swift_name("serializer()")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("IndyCredentialForTheRequest")))
@interface Ssi_agentIndyCredentialForTheRequest : Ssi_agentBase
- (instancetype)initWithCredInfo:(Ssi_agentIndyCredInfo *)credInfo interval:(Ssi_agentInterval * _Nullable)interval __attribute__((swift_name("init(credInfo:interval:)"))) __attribute__((objc_designated_initializer));
- (Ssi_agentIndyCredInfo *)component1 __attribute__((swift_name("component1()")));
- (Ssi_agentInterval * _Nullable)component2 __attribute__((swift_name("component2()")));
- (Ssi_agentIndyCredentialForTheRequest *)doCopyCredInfo:(Ssi_agentIndyCredInfo *)credInfo interval:(Ssi_agentInterval * _Nullable)interval __attribute__((swift_name("doCopy(credInfo:interval:)")));
- (BOOL)isEqual:(id _Nullable)other __attribute__((swift_name("isEqual(_:)")));
- (NSUInteger)hash __attribute__((swift_name("hash()")));
- (NSString *)description __attribute__((swift_name("description()")));
@property (readonly) Ssi_agentIndyCredInfo *credInfo __attribute__((swift_name("credInfo")));
@property (readonly) Ssi_agentInterval * _Nullable interval __attribute__((swift_name("interval")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("IndyCredentialForTheRequest.Companion")))
@interface Ssi_agentIndyCredentialForTheRequestCompanion : Ssi_agentBase
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
+ (instancetype)companion __attribute__((swift_name("init()")));
- (id<Ssi_agentKotlinx_serialization_coreKSerializer>)serializer __attribute__((swift_name("serializer()")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("IndyPresentation")))
@interface Ssi_agentIndyPresentation : Ssi_agentBase <Ssi_agentPresentation>
- (instancetype)initWithIndyProof:(Ssi_agentIndyProof *)indyProof requestedProof:(Ssi_agentRequestedProof *)requestedProof identifiers:(NSArray<Ssi_agentProofIdentifier *> *)identifiers __attribute__((swift_name("init(indyProof:requestedProof:identifiers:)"))) __attribute__((objc_designated_initializer));
- (Ssi_agentIndyProof *)component1 __attribute__((swift_name("component1()")));
- (Ssi_agentRequestedProof *)component2 __attribute__((swift_name("component2()")));
- (NSArray<Ssi_agentProofIdentifier *> *)component3 __attribute__((swift_name("component3()")));
- (Ssi_agentIndyPresentation *)doCopyIndyProof:(Ssi_agentIndyProof *)indyProof requestedProof:(Ssi_agentRequestedProof *)requestedProof identifiers:(NSArray<Ssi_agentProofIdentifier *> *)identifiers __attribute__((swift_name("doCopy(indyProof:requestedProof:identifiers:)")));
- (BOOL)isEqual:(id _Nullable)other __attribute__((swift_name("isEqual(_:)")));
- (NSUInteger)hash __attribute__((swift_name("hash()")));
- (NSString *)description __attribute__((swift_name("description()")));
@property (readonly) NSArray<Ssi_agentProofIdentifier *> *identifiers __attribute__((swift_name("identifiers")));
@property (readonly) Ssi_agentIndyProof *indyProof __attribute__((swift_name("indyProof")));
@property (readonly) Ssi_agentRequestedProof *requestedProof __attribute__((swift_name("requestedProof")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("IndyPresentation.Companion")))
@interface Ssi_agentIndyPresentationCompanion : Ssi_agentBase
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
+ (instancetype)companion __attribute__((swift_name("init()")));
- (id<Ssi_agentKotlinx_serialization_coreKSerializer>)serializer __attribute__((swift_name("serializer()")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("IndyPresentationRequest")))
@interface Ssi_agentIndyPresentationRequest : Ssi_agentBase <Ssi_agentPresentationRequest>
- (instancetype)initWithName:(NSString *)name version:(NSString *)version nonce:(NSString *)nonce requestedAttributes:(NSDictionary<NSString *, Ssi_agentCredentialAttributeReference *> *)requestedAttributes requestedPredicates:(NSDictionary<NSString *, Ssi_agentCredentialPredicateReference *> *)requestedPredicates nonRevoked:(Ssi_agentInterval * _Nullable)nonRevoked __attribute__((swift_name("init(name:version:nonce:requestedAttributes:requestedPredicates:nonRevoked:)"))) __attribute__((objc_designated_initializer));
- (NSString *)component1 __attribute__((swift_name("component1()")));
- (NSString *)component2 __attribute__((swift_name("component2()")));
- (NSString *)component3 __attribute__((swift_name("component3()")));
- (NSDictionary<NSString *, Ssi_agentCredentialAttributeReference *> *)component4 __attribute__((swift_name("component4()")));
- (NSDictionary<NSString *, Ssi_agentCredentialPredicateReference *> *)component5 __attribute__((swift_name("component5()")));
- (Ssi_agentInterval * _Nullable)component6 __attribute__((swift_name("component6()")));
- (Ssi_agentIndyPresentationRequest *)doCopyName:(NSString *)name version:(NSString *)version nonce:(NSString *)nonce requestedAttributes:(NSDictionary<NSString *, Ssi_agentCredentialAttributeReference *> *)requestedAttributes requestedPredicates:(NSDictionary<NSString *, Ssi_agentCredentialPredicateReference *> *)requestedPredicates nonRevoked:(Ssi_agentInterval * _Nullable)nonRevoked __attribute__((swift_name("doCopy(name:version:nonce:requestedAttributes:requestedPredicates:nonRevoked:)")));
- (BOOL)isEqual:(id _Nullable)other __attribute__((swift_name("isEqual(_:)")));
- (NSUInteger)hash __attribute__((swift_name("hash()")));
- (NSString *)description __attribute__((swift_name("description()")));
@property (readonly) NSString *name __attribute__((swift_name("name")));
@property (readonly) Ssi_agentInterval * _Nullable nonRevoked __attribute__((swift_name("nonRevoked")));
@property (readonly) NSString *nonce __attribute__((swift_name("nonce")));
@property (readonly) NSDictionary<NSString *, Ssi_agentCredentialAttributeReference *> *requestedAttributes __attribute__((swift_name("requestedAttributes")));
@property (readonly) NSDictionary<NSString *, Ssi_agentCredentialPredicateReference *> *requestedPredicates __attribute__((swift_name("requestedPredicates")));
@property (readonly) NSString *version __attribute__((swift_name("version")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("IndyPresentationRequest.Companion")))
@interface Ssi_agentIndyPresentationRequestCompanion : Ssi_agentBase
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
+ (instancetype)companion __attribute__((swift_name("init()")));
- (id<Ssi_agentKotlinx_serialization_coreKSerializer>)serializer __attribute__((swift_name("serializer()")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("IndyProof")))
@interface Ssi_agentIndyProof : Ssi_agentBase
- (instancetype)initWithProofs:(NSArray<Ssi_agentIndyProofDetails *> *)proofs aggregatedProof:(NSString *)aggregatedProof __attribute__((swift_name("init(proofs:aggregatedProof:)"))) __attribute__((objc_designated_initializer));
- (NSArray<Ssi_agentIndyProofDetails *> *)component1 __attribute__((swift_name("component1()")));
- (NSString *)component2 __attribute__((swift_name("component2()")));
- (Ssi_agentIndyProof *)doCopyProofs:(NSArray<Ssi_agentIndyProofDetails *> *)proofs aggregatedProof:(NSString *)aggregatedProof __attribute__((swift_name("doCopy(proofs:aggregatedProof:)")));
- (BOOL)isEqual:(id _Nullable)other __attribute__((swift_name("isEqual(_:)")));
- (NSUInteger)hash __attribute__((swift_name("hash()")));
- (NSString *)description __attribute__((swift_name("description()")));
@property (readonly) NSString *aggregatedProof __attribute__((swift_name("aggregatedProof")));
@property (readonly) NSArray<Ssi_agentIndyProofDetails *> *proofs __attribute__((swift_name("proofs")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("IndyProof.Companion")))
@interface Ssi_agentIndyProofCompanion : Ssi_agentBase
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
+ (instancetype)companion __attribute__((swift_name("init()")));
- (id<Ssi_agentKotlinx_serialization_coreKSerializer>)serializer __attribute__((swift_name("serializer()")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("IndyProofDetails")))
@interface Ssi_agentIndyProofDetails : Ssi_agentBase
- (instancetype)initWithPrimaryProof:(NSString *)primaryProof nonRevokedProof:(NSString * _Nullable)nonRevokedProof __attribute__((swift_name("init(primaryProof:nonRevokedProof:)"))) __attribute__((objc_designated_initializer));
- (NSString *)component1 __attribute__((swift_name("component1()")));
- (NSString * _Nullable)component2 __attribute__((swift_name("component2()")));
- (Ssi_agentIndyProofDetails *)doCopyPrimaryProof:(NSString *)primaryProof nonRevokedProof:(NSString * _Nullable)nonRevokedProof __attribute__((swift_name("doCopy(primaryProof:nonRevokedProof:)")));
- (BOOL)isEqual:(id _Nullable)other __attribute__((swift_name("isEqual(_:)")));
- (NSUInteger)hash __attribute__((swift_name("hash()")));
- (NSString *)description __attribute__((swift_name("description()")));
@property (readonly) NSString * _Nullable nonRevokedProof __attribute__((swift_name("nonRevokedProof")));
@property (readonly) NSString *primaryProof __attribute__((swift_name("primaryProof")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("IndyProofDetails.Companion")))
@interface Ssi_agentIndyProofDetailsCompanion : Ssi_agentBase
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
+ (instancetype)companion __attribute__((swift_name("init()")));
- (id<Ssi_agentKotlinx_serialization_coreKSerializer>)serializer __attribute__((swift_name("serializer()")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("IndySchema")))
@interface Ssi_agentIndySchema : Ssi_agentBase <Ssi_agentSchema>
- (instancetype)initWithVer:(NSString *)ver id:(NSString *)id name:(NSString *)name version:(NSString *)version attributeNames:(NSArray<NSString *> *)attributeNames seqNo:(Ssi_agentInt * _Nullable)seqNo __attribute__((swift_name("init(ver:id:name:version:attributeNames:seqNo:)"))) __attribute__((objc_designated_initializer));
- (NSString *)component1 __attribute__((swift_name("component1()")));
- (NSString *)component2 __attribute__((swift_name("component2()")));
- (NSString *)component3 __attribute__((swift_name("component3()")));
- (NSString *)component4 __attribute__((swift_name("component4()")));
- (NSArray<NSString *> *)component5 __attribute__((swift_name("component5()")));
- (Ssi_agentInt * _Nullable)component6 __attribute__((swift_name("component6()")));
- (Ssi_agentIndySchema *)doCopyVer:(NSString *)ver id:(NSString *)id name:(NSString *)name version:(NSString *)version attributeNames:(NSArray<NSString *> *)attributeNames seqNo:(Ssi_agentInt * _Nullable)seqNo __attribute__((swift_name("doCopy(ver:id:name:version:attributeNames:seqNo:)")));
- (BOOL)isEqual:(id _Nullable)other __attribute__((swift_name("isEqual(_:)")));
- (NSUInteger)hash __attribute__((swift_name("hash()")));
- (NSString *)description __attribute__((swift_name("description()")));
@property (readonly) NSArray<NSString *> *attributeNames __attribute__((swift_name("attributeNames")));
@property (readonly) NSString *id __attribute__((swift_name("id")));
@property (readonly) NSString *name __attribute__((swift_name("name")));
@property (readonly) Ssi_agentInt * _Nullable seqNo __attribute__((swift_name("seqNo")));
@property (readonly) NSString *ver __attribute__((swift_name("ver")));
@property (readonly) NSString *version __attribute__((swift_name("version")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("IndySchema.Companion")))
@interface Ssi_agentIndySchemaCompanion : Ssi_agentBase
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
+ (instancetype)companion __attribute__((swift_name("init()")));
- (id<Ssi_agentKotlinx_serialization_coreKSerializer>)serializer __attribute__((swift_name("serializer()")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("IndySchemaId")))
@interface Ssi_agentIndySchemaId : Ssi_agentBase <Ssi_agentSchemaId>
- (instancetype)initWithDid:(NSString *)did name:(NSString *)name version:(NSString *)version __attribute__((swift_name("init(did:name:version:)"))) __attribute__((objc_designated_initializer));
- (NSString *)description __attribute__((swift_name("description()")));
@property (readonly) NSString *did __attribute__((swift_name("did")));
@property (readonly) NSString *name __attribute__((swift_name("name")));
@property (readonly) NSString *version __attribute__((swift_name("version")));
@end;

__attribute__((swift_name("FromString")))
@protocol Ssi_agentFromString
@required
- (id)fromStringStr:(NSString *)str __attribute__((swift_name("fromString(str:)")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("IndySchemaId.Companion")))
@interface Ssi_agentIndySchemaIdCompanion : Ssi_agentBase <Ssi_agentFromString>
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
+ (instancetype)companion __attribute__((swift_name("init()")));
- (Ssi_agentIndySchemaId *)fromStringStr:(NSString *)str __attribute__((swift_name("fromString(str:)")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("Interval")))
@interface Ssi_agentInterval : Ssi_agentBase
- (instancetype)initWithFrom:(Ssi_agentLong * _Nullable)from to:(int64_t)to __attribute__((swift_name("init(from:to:)"))) __attribute__((objc_designated_initializer));
- (Ssi_agentLong * _Nullable)component1 __attribute__((swift_name("component1()")));
- (int64_t)component2 __attribute__((swift_name("component2()")));
- (Ssi_agentInterval *)doCopyFrom:(Ssi_agentLong * _Nullable)from to:(int64_t)to __attribute__((swift_name("doCopy(from:to:)")));
- (BOOL)isEqual:(id _Nullable)other __attribute__((swift_name("isEqual(_:)")));
- (NSUInteger)hash __attribute__((swift_name("hash()")));
- (NSString *)description __attribute__((swift_name("description()")));
@property (readonly) Ssi_agentLong * _Nullable from __attribute__((swift_name("from")));
@property (readonly) int64_t to __attribute__((swift_name("to")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("Interval.Companion")))
@interface Ssi_agentIntervalCompanion : Ssi_agentBase
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
+ (instancetype)companion __attribute__((swift_name("init()")));
- (Ssi_agentInterval *)allTime __attribute__((swift_name("allTime()")));
- (id<Ssi_agentKotlinx_serialization_coreKSerializer>)serializer __attribute__((swift_name("serializer()")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("ProofIdentifier")))
@interface Ssi_agentProofIdentifier : Ssi_agentBase
- (instancetype)initWithSchemaIdRaw:(NSString *)schemaIdRaw credentialDefinitionIdRaw:(NSString *)credentialDefinitionIdRaw revocationRegistryIdRaw:(NSString * _Nullable)revocationRegistryIdRaw timestamp:(Ssi_agentLong * _Nullable)timestamp __attribute__((swift_name("init(schemaIdRaw:credentialDefinitionIdRaw:revocationRegistryIdRaw:timestamp:)"))) __attribute__((objc_designated_initializer));
- (NSString *)component1 __attribute__((swift_name("component1()")));
- (NSString *)component2 __attribute__((swift_name("component2()")));
- (NSString * _Nullable)component3 __attribute__((swift_name("component3()")));
- (Ssi_agentLong * _Nullable)component4 __attribute__((swift_name("component4()")));
- (Ssi_agentProofIdentifier *)doCopySchemaIdRaw:(NSString *)schemaIdRaw credentialDefinitionIdRaw:(NSString *)credentialDefinitionIdRaw revocationRegistryIdRaw:(NSString * _Nullable)revocationRegistryIdRaw timestamp:(Ssi_agentLong * _Nullable)timestamp __attribute__((swift_name("doCopy(schemaIdRaw:credentialDefinitionIdRaw:revocationRegistryIdRaw:timestamp:)")));
- (BOOL)isEqual:(id _Nullable)other __attribute__((swift_name("isEqual(_:)")));
- (NSUInteger)hash __attribute__((swift_name("hash()")));
- (NSString *)description __attribute__((swift_name("description()")));
@property (readonly) NSString *credentialDefinitionIdRaw __attribute__((swift_name("credentialDefinitionIdRaw")));
@property (readonly) NSString * _Nullable revocationRegistryIdRaw __attribute__((swift_name("revocationRegistryIdRaw")));
@property (readonly) NSString *schemaIdRaw __attribute__((swift_name("schemaIdRaw")));
@property (readonly) Ssi_agentLong * _Nullable timestamp __attribute__((swift_name("timestamp")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("ProofIdentifier.Companion")))
@interface Ssi_agentProofIdentifierCompanion : Ssi_agentBase
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
+ (instancetype)companion __attribute__((swift_name("init()")));
- (id<Ssi_agentKotlinx_serialization_coreKSerializer>)serializer __attribute__((swift_name("serializer()")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("RequestedAttributeInfo")))
@interface Ssi_agentRequestedAttributeInfo : Ssi_agentBase
- (instancetype)initWithCredentialId:(NSString *)credentialId revealed:(BOOL)revealed timestamp:(Ssi_agentLong * _Nullable)timestamp __attribute__((swift_name("init(credentialId:revealed:timestamp:)"))) __attribute__((objc_designated_initializer));
- (NSString *)component1 __attribute__((swift_name("component1()")));
- (BOOL)component2 __attribute__((swift_name("component2()")));
- (Ssi_agentLong * _Nullable)component3 __attribute__((swift_name("component3()")));
- (Ssi_agentRequestedAttributeInfo *)doCopyCredentialId:(NSString *)credentialId revealed:(BOOL)revealed timestamp:(Ssi_agentLong * _Nullable)timestamp __attribute__((swift_name("doCopy(credentialId:revealed:timestamp:)")));
- (BOOL)isEqual:(id _Nullable)other __attribute__((swift_name("isEqual(_:)")));
- (NSUInteger)hash __attribute__((swift_name("hash()")));
- (NSString *)description __attribute__((swift_name("description()")));
@property (readonly) NSString *credentialId __attribute__((swift_name("credentialId")));
@property (readonly) BOOL revealed __attribute__((swift_name("revealed")));
@property (readonly) Ssi_agentLong * _Nullable timestamp __attribute__((swift_name("timestamp")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("RequestedAttributeInfo.Companion")))
@interface Ssi_agentRequestedAttributeInfoCompanion : Ssi_agentBase
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
+ (instancetype)companion __attribute__((swift_name("init()")));
- (id<Ssi_agentKotlinx_serialization_coreKSerializer>)serializer __attribute__((swift_name("serializer()")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("RequestedCredentials")))
@interface Ssi_agentRequestedCredentials : Ssi_agentBase
- (instancetype)initWithRequestedAttributes:(NSDictionary<NSString *, Ssi_agentRequestedAttributeInfo *> *)requestedAttributes requestedPredicates:(NSDictionary<NSString *, Ssi_agentRequestedPredicateInfo *> *)requestedPredicates selfAttestedAttributes:(NSDictionary<NSString *, NSString *> *)selfAttestedAttributes __attribute__((swift_name("init(requestedAttributes:requestedPredicates:selfAttestedAttributes:)"))) __attribute__((objc_designated_initializer));
- (NSDictionary<NSString *, Ssi_agentRequestedAttributeInfo *> *)component1 __attribute__((swift_name("component1()")));
- (NSDictionary<NSString *, Ssi_agentRequestedPredicateInfo *> *)component2 __attribute__((swift_name("component2()")));
- (NSDictionary<NSString *, NSString *> *)component3 __attribute__((swift_name("component3()")));
- (Ssi_agentRequestedCredentials *)doCopyRequestedAttributes:(NSDictionary<NSString *, Ssi_agentRequestedAttributeInfo *> *)requestedAttributes requestedPredicates:(NSDictionary<NSString *, Ssi_agentRequestedPredicateInfo *> *)requestedPredicates selfAttestedAttributes:(NSDictionary<NSString *, NSString *> *)selfAttestedAttributes __attribute__((swift_name("doCopy(requestedAttributes:requestedPredicates:selfAttestedAttributes:)")));
- (BOOL)isEqual:(id _Nullable)other __attribute__((swift_name("isEqual(_:)")));
- (NSUInteger)hash __attribute__((swift_name("hash()")));
- (NSString *)description __attribute__((swift_name("description()")));
@property (readonly) NSDictionary<NSString *, Ssi_agentRequestedAttributeInfo *> *requestedAttributes __attribute__((swift_name("requestedAttributes")));
@property (readonly) NSDictionary<NSString *, Ssi_agentRequestedPredicateInfo *> *requestedPredicates __attribute__((swift_name("requestedPredicates")));
@property (readonly) NSDictionary<NSString *, NSString *> *selfAttestedAttributes __attribute__((swift_name("selfAttestedAttributes")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("RequestedCredentials.Companion")))
@interface Ssi_agentRequestedCredentialsCompanion : Ssi_agentBase
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
+ (instancetype)companion __attribute__((swift_name("init()")));
- (id<Ssi_agentKotlinx_serialization_coreKSerializer>)serializer __attribute__((swift_name("serializer()")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("RequestedPredicateInfo")))
@interface Ssi_agentRequestedPredicateInfo : Ssi_agentBase
- (instancetype)initWithCredentialId:(NSString *)credentialId timestamp:(Ssi_agentLong * _Nullable)timestamp __attribute__((swift_name("init(credentialId:timestamp:)"))) __attribute__((objc_designated_initializer));
- (NSString *)component1 __attribute__((swift_name("component1()")));
- (Ssi_agentLong * _Nullable)component2 __attribute__((swift_name("component2()")));
- (Ssi_agentRequestedPredicateInfo *)doCopyCredentialId:(NSString *)credentialId timestamp:(Ssi_agentLong * _Nullable)timestamp __attribute__((swift_name("doCopy(credentialId:timestamp:)")));
- (BOOL)isEqual:(id _Nullable)other __attribute__((swift_name("isEqual(_:)")));
- (NSUInteger)hash __attribute__((swift_name("hash()")));
- (NSString *)description __attribute__((swift_name("description()")));
@property (readonly) NSString *credentialId __attribute__((swift_name("credentialId")));
@property (readonly) Ssi_agentLong * _Nullable timestamp __attribute__((swift_name("timestamp")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("RequestedPredicateInfo.Companion")))
@interface Ssi_agentRequestedPredicateInfoCompanion : Ssi_agentBase
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
+ (instancetype)companion __attribute__((swift_name("init()")));
- (id<Ssi_agentKotlinx_serialization_coreKSerializer>)serializer __attribute__((swift_name("serializer()")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("RequestedProof")))
@interface Ssi_agentRequestedProof : Ssi_agentBase
- (instancetype)initWithRevealedAttrs:(NSDictionary<NSString *, Ssi_agentRevealedAttributeReference *> *)revealedAttrs selfAttestedAttrs:(NSDictionary<NSString *, Ssi_agentRevealedAttributeReference *> *)selfAttestedAttrs unrevealedAttrs:(NSDictionary<NSString *, Ssi_agentCredentialReference *> *)unrevealedAttrs predicates:(NSDictionary<NSString *, Ssi_agentRevealedPredicateReference *> *)predicates __attribute__((swift_name("init(revealedAttrs:selfAttestedAttrs:unrevealedAttrs:predicates:)"))) __attribute__((objc_designated_initializer));
- (NSDictionary<NSString *, Ssi_agentRevealedAttributeReference *> *)component1 __attribute__((swift_name("component1()")));
- (NSDictionary<NSString *, Ssi_agentRevealedAttributeReference *> *)component2 __attribute__((swift_name("component2()")));
- (NSDictionary<NSString *, Ssi_agentCredentialReference *> *)component3 __attribute__((swift_name("component3()")));
- (NSDictionary<NSString *, Ssi_agentRevealedPredicateReference *> *)component4 __attribute__((swift_name("component4()")));
- (Ssi_agentRequestedProof *)doCopyRevealedAttrs:(NSDictionary<NSString *, Ssi_agentRevealedAttributeReference *> *)revealedAttrs selfAttestedAttrs:(NSDictionary<NSString *, Ssi_agentRevealedAttributeReference *> *)selfAttestedAttrs unrevealedAttrs:(NSDictionary<NSString *, Ssi_agentCredentialReference *> *)unrevealedAttrs predicates:(NSDictionary<NSString *, Ssi_agentRevealedPredicateReference *> *)predicates __attribute__((swift_name("doCopy(revealedAttrs:selfAttestedAttrs:unrevealedAttrs:predicates:)")));
- (BOOL)isEqual:(id _Nullable)other __attribute__((swift_name("isEqual(_:)")));
- (NSUInteger)hash __attribute__((swift_name("hash()")));
- (NSString *)description __attribute__((swift_name("description()")));
@property (readonly) NSDictionary<NSString *, Ssi_agentRevealedPredicateReference *> *predicates __attribute__((swift_name("predicates")));
@property (readonly) NSDictionary<NSString *, Ssi_agentRevealedAttributeReference *> *revealedAttrs __attribute__((swift_name("revealedAttrs")));
@property (readonly) NSDictionary<NSString *, Ssi_agentRevealedAttributeReference *> *selfAttestedAttrs __attribute__((swift_name("selfAttestedAttrs")));
@property (readonly) NSDictionary<NSString *, Ssi_agentCredentialReference *> *unrevealedAttrs __attribute__((swift_name("unrevealedAttrs")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("RequestedProof.Companion")))
@interface Ssi_agentRequestedProofCompanion : Ssi_agentBase
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
+ (instancetype)companion __attribute__((swift_name("init()")));
- (id<Ssi_agentKotlinx_serialization_coreKSerializer>)serializer __attribute__((swift_name("serializer()")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("Restriction")))
@interface Ssi_agentRestriction : Ssi_agentBase
- (instancetype)initWithSchemaName:(NSString * _Nullable)schemaName __attribute__((swift_name("init(schemaName:)"))) __attribute__((objc_designated_initializer));
- (NSString * _Nullable)component1 __attribute__((swift_name("component1()")));
- (Ssi_agentRestriction *)doCopySchemaName:(NSString * _Nullable)schemaName __attribute__((swift_name("doCopy(schemaName:)")));
- (BOOL)isEqual:(id _Nullable)other __attribute__((swift_name("isEqual(_:)")));
- (NSUInteger)hash __attribute__((swift_name("hash()")));
- (NSString *)description __attribute__((swift_name("description()")));
@property (readonly) NSString * _Nullable schemaName __attribute__((swift_name("schemaName")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("Restriction.Companion")))
@interface Ssi_agentRestrictionCompanion : Ssi_agentBase
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
+ (instancetype)companion __attribute__((swift_name("init()")));
- (id<Ssi_agentKotlinx_serialization_coreKSerializer>)serializer __attribute__((swift_name("serializer()")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("RevealedAttributeReference")))
@interface Ssi_agentRevealedAttributeReference : Ssi_agentBase
- (instancetype)initWithSubProofIndex:(int32_t)subProofIndex raw:(NSString *)raw encoded:(NSString *)encoded __attribute__((swift_name("init(subProofIndex:raw:encoded:)"))) __attribute__((objc_designated_initializer));
- (int32_t)component1 __attribute__((swift_name("component1()")));
- (NSString *)component2 __attribute__((swift_name("component2()")));
- (NSString *)component3 __attribute__((swift_name("component3()")));
- (Ssi_agentRevealedAttributeReference *)doCopySubProofIndex:(int32_t)subProofIndex raw:(NSString *)raw encoded:(NSString *)encoded __attribute__((swift_name("doCopy(subProofIndex:raw:encoded:)")));
- (BOOL)isEqual:(id _Nullable)other __attribute__((swift_name("isEqual(_:)")));
- (NSUInteger)hash __attribute__((swift_name("hash()")));
- (NSString *)description __attribute__((swift_name("description()")));
@property (readonly) NSString *encoded __attribute__((swift_name("encoded")));
@property (readonly) NSString *raw __attribute__((swift_name("raw")));
@property (readonly) int32_t subProofIndex __attribute__((swift_name("subProofIndex")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("RevealedAttributeReference.Companion")))
@interface Ssi_agentRevealedAttributeReferenceCompanion : Ssi_agentBase
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
+ (instancetype)companion __attribute__((swift_name("init()")));
- (id<Ssi_agentKotlinx_serialization_coreKSerializer>)serializer __attribute__((swift_name("serializer()")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("RevealedPredicateReference")))
@interface Ssi_agentRevealedPredicateReference : Ssi_agentBase
- (instancetype)initWithSubProofIndex:(int32_t)subProofIndex __attribute__((swift_name("init(subProofIndex:)"))) __attribute__((objc_designated_initializer));
- (int32_t)component1 __attribute__((swift_name("component1()")));
- (Ssi_agentRevealedPredicateReference *)doCopySubProofIndex:(int32_t)subProofIndex __attribute__((swift_name("doCopy(subProofIndex:)")));
- (BOOL)isEqual:(id _Nullable)other __attribute__((swift_name("isEqual(_:)")));
- (NSUInteger)hash __attribute__((swift_name("hash()")));
- (NSString *)description __attribute__((swift_name("description()")));
@property (readonly) int32_t subProofIndex __attribute__((swift_name("subProofIndex")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("RevealedPredicateReference.Companion")))
@interface Ssi_agentRevealedPredicateReferenceCompanion : Ssi_agentBase
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
+ (instancetype)companion __attribute__((swift_name("init()")));
- (id<Ssi_agentKotlinx_serialization_coreKSerializer>)serializer __attribute__((swift_name("serializer()")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("RevocationRegistryEntry")))
@interface Ssi_agentRevocationRegistryEntry : Ssi_agentBase
- (instancetype)initWithVer:(NSString *)ver value:(NSString *)value __attribute__((swift_name("init(ver:value:)"))) __attribute__((objc_designated_initializer));
- (NSString *)component1 __attribute__((swift_name("component1()")));
- (NSString *)component2 __attribute__((swift_name("component2()")));
- (Ssi_agentRevocationRegistryEntry *)doCopyVer:(NSString *)ver value:(NSString *)value __attribute__((swift_name("doCopy(ver:value:)")));
- (BOOL)isEqual:(id _Nullable)other __attribute__((swift_name("isEqual(_:)")));
- (NSUInteger)hash __attribute__((swift_name("hash()")));
- (NSString *)description __attribute__((swift_name("description()")));
@property (readonly) NSString *value __attribute__((swift_name("value")));
@property (readonly) NSString *ver __attribute__((swift_name("ver")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("RevocationRegistryEntry.Companion")))
@interface Ssi_agentRevocationRegistryEntryCompanion : Ssi_agentBase
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
+ (instancetype)companion __attribute__((swift_name("init()")));
- (id<Ssi_agentKotlinx_serialization_coreKSerializer>)serializer __attribute__((swift_name("serializer()")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("Timestamp")))
@interface Ssi_agentTimestamp : Ssi_agentBase
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
+ (instancetype)timestamp __attribute__((swift_name("init()")));
- (int64_t)now __attribute__((swift_name("now()")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("IndyRevocationRegistryDefinition")))
@interface Ssi_agentIndyRevocationRegistryDefinition : Ssi_agentBase <Ssi_agentRevocationRegistryDefinition>
- (instancetype)initWithVer:(NSString *)ver id:(NSString *)id revocationRegistryDefinitionType:(NSString *)revocationRegistryDefinitionType tag:(NSString *)tag credentialDefinitionIdRaw:(NSString *)credentialDefinitionIdRaw value:(NSString *)value __attribute__((swift_name("init(ver:id:revocationRegistryDefinitionType:tag:credentialDefinitionIdRaw:value:)"))) __attribute__((objc_designated_initializer));
- (NSString *)component1 __attribute__((swift_name("component1()")));
- (NSString *)component2 __attribute__((swift_name("component2()")));
- (NSString *)component3 __attribute__((swift_name("component3()")));
- (NSString *)component4 __attribute__((swift_name("component4()")));
- (NSString *)component5 __attribute__((swift_name("component5()")));
- (NSString *)component6 __attribute__((swift_name("component6()")));
- (Ssi_agentIndyRevocationRegistryDefinition *)doCopyVer:(NSString *)ver id:(NSString *)id revocationRegistryDefinitionType:(NSString *)revocationRegistryDefinitionType tag:(NSString *)tag credentialDefinitionIdRaw:(NSString *)credentialDefinitionIdRaw value:(NSString *)value __attribute__((swift_name("doCopy(ver:id:revocationRegistryDefinitionType:tag:credentialDefinitionIdRaw:value:)")));
- (BOOL)isEqual:(id _Nullable)other __attribute__((swift_name("isEqual(_:)")));
- (NSUInteger)hash __attribute__((swift_name("hash()")));
- (NSString *)description __attribute__((swift_name("description()")));
@property (readonly) NSString *credentialDefinitionIdRaw __attribute__((swift_name("credentialDefinitionIdRaw")));
@property (readonly) NSString *id __attribute__((swift_name("id")));
@property (readonly) NSString *revocationRegistryDefinitionType __attribute__((swift_name("revocationRegistryDefinitionType")));
@property (readonly) NSString *tag __attribute__((swift_name("tag")));
@property (readonly) NSString *value __attribute__((swift_name("value")));
@property (readonly) NSString *ver __attribute__((swift_name("ver")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("IndyRevocationRegistryDefinition.Companion")))
@interface Ssi_agentIndyRevocationRegistryDefinitionCompanion : Ssi_agentBase
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
+ (instancetype)companion __attribute__((swift_name("init()")));
- (id<Ssi_agentKotlinx_serialization_coreKSerializer>)serializer __attribute__((swift_name("serializer()")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("RevocationState")))
@interface Ssi_agentRevocationState : Ssi_agentBase
- (instancetype)initWithWitness:(NSString *)witness revocationRegistry:(NSString *)revocationRegistry timestamp:(int64_t)timestamp revocationRegistryIdRaw:(NSString * _Nullable)revocationRegistryIdRaw __attribute__((swift_name("init(witness:revocationRegistry:timestamp:revocationRegistryIdRaw:)"))) __attribute__((objc_designated_initializer));
- (NSString *)component1 __attribute__((swift_name("component1()")));
- (NSString *)component2 __attribute__((swift_name("component2()")));
- (int64_t)component3 __attribute__((swift_name("component3()")));
- (NSString * _Nullable)component4 __attribute__((swift_name("component4()")));
- (Ssi_agentRevocationState *)doCopyWitness:(NSString *)witness revocationRegistry:(NSString *)revocationRegistry timestamp:(int64_t)timestamp revocationRegistryIdRaw:(NSString * _Nullable)revocationRegistryIdRaw __attribute__((swift_name("doCopy(witness:revocationRegistry:timestamp:revocationRegistryIdRaw:)")));
- (BOOL)isEqual:(id _Nullable)other __attribute__((swift_name("isEqual(_:)")));
- (NSUInteger)hash __attribute__((swift_name("hash()")));
- (NSString *)description __attribute__((swift_name("description()")));
@property (readonly) NSString *revocationRegistry __attribute__((swift_name("revocationRegistry")));
@property NSString * _Nullable revocationRegistryIdRaw __attribute__((swift_name("revocationRegistryIdRaw")));
@property (readonly) int64_t timestamp __attribute__((swift_name("timestamp")));
@property (readonly) NSString *witness __attribute__((swift_name("witness")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("RevocationState.Companion")))
@interface Ssi_agentRevocationStateCompanion : Ssi_agentBase
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
+ (instancetype)companion __attribute__((swift_name("init()")));
- (id<Ssi_agentKotlinx_serialization_coreKSerializer>)serializer __attribute__((swift_name("serializer()")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("IndyCredential")))
@interface Ssi_agentIndyCredential : Ssi_agentBase <Ssi_agentCredential>
- (instancetype)initWithSchemaIdRaw:(NSString *)schemaIdRaw credentialDefinitionIdRaw:(NSString *)credentialDefinitionIdRaw revocationRegistry:(NSString * _Nullable)revocationRegistry witness:(NSString * _Nullable)witness revocationRegistryIdRaw:(NSString * _Nullable)revocationRegistryIdRaw values:(NSString *)values signature:(NSString *)signature signatureCorrectnessProof:(NSString *)signatureCorrectnessProof __attribute__((swift_name("init(schemaIdRaw:credentialDefinitionIdRaw:revocationRegistry:witness:revocationRegistryIdRaw:values:signature:signatureCorrectnessProof:)"))) __attribute__((objc_designated_initializer));
- (NSString *)component1 __attribute__((swift_name("component1()")));
- (NSString *)component2 __attribute__((swift_name("component2()")));
- (NSString * _Nullable)component3 __attribute__((swift_name("component3()")));
- (NSString * _Nullable)component4 __attribute__((swift_name("component4()")));
- (NSString * _Nullable)component5 __attribute__((swift_name("component5()")));
- (NSString *)component6 __attribute__((swift_name("component6()")));
- (NSString *)component7 __attribute__((swift_name("component7()")));
- (NSString *)component8 __attribute__((swift_name("component8()")));
- (Ssi_agentIndyCredential *)doCopySchemaIdRaw:(NSString *)schemaIdRaw credentialDefinitionIdRaw:(NSString *)credentialDefinitionIdRaw revocationRegistry:(NSString * _Nullable)revocationRegistry witness:(NSString * _Nullable)witness revocationRegistryIdRaw:(NSString * _Nullable)revocationRegistryIdRaw values:(NSString *)values signature:(NSString *)signature signatureCorrectnessProof:(NSString *)signatureCorrectnessProof __attribute__((swift_name("doCopy(schemaIdRaw:credentialDefinitionIdRaw:revocationRegistry:witness:revocationRegistryIdRaw:values:signature:signatureCorrectnessProof:)")));
- (BOOL)isEqual:(id _Nullable)other __attribute__((swift_name("isEqual(_:)")));
- (NSUInteger)hash __attribute__((swift_name("hash()")));
- (NSString *)description __attribute__((swift_name("description()")));
@property (readonly) NSString *credentialDefinitionIdRaw __attribute__((swift_name("credentialDefinitionIdRaw")));
@property (readonly) NSString * _Nullable revocationRegistry __attribute__((swift_name("revocationRegistry")));
@property (readonly) NSString * _Nullable revocationRegistryIdRaw __attribute__((swift_name("revocationRegistryIdRaw")));
@property (readonly) NSString *schemaIdRaw __attribute__((swift_name("schemaIdRaw")));
@property (readonly) NSString *signature __attribute__((swift_name("signature")));
@property (readonly) NSString *signatureCorrectnessProof __attribute__((swift_name("signatureCorrectnessProof")));
@property (readonly) NSString *values __attribute__((swift_name("values")));
@property (readonly) NSString * _Nullable witness __attribute__((swift_name("witness")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("IndyCredential.Companion")))
@interface Ssi_agentIndyCredentialCompanion : Ssi_agentBase
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
+ (instancetype)companion __attribute__((swift_name("init()")));
- (id<Ssi_agentKotlinx_serialization_coreKSerializer>)serializer __attribute__((swift_name("serializer()")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("IndyCredentialDefinition")))
@interface Ssi_agentIndyCredentialDefinition : Ssi_agentBase <Ssi_agentCredentialDefinition>
- (instancetype)initWithVer:(NSString *)ver id:(NSString *)id schemaSeqNo:(NSString *)schemaSeqNo type:(NSString *)type tag:(NSString *)tag value:(Ssi_agentIndyCredentialPubKeys *)value __attribute__((swift_name("init(ver:id:schemaSeqNo:type:tag:value:)"))) __attribute__((objc_designated_initializer));
- (NSString *)component1 __attribute__((swift_name("component1()")));
- (NSString *)component2 __attribute__((swift_name("component2()")));
- (NSString *)component3 __attribute__((swift_name("component3()")));
- (NSString *)component4 __attribute__((swift_name("component4()")));
- (NSString *)component5 __attribute__((swift_name("component5()")));
- (Ssi_agentIndyCredentialPubKeys *)component6 __attribute__((swift_name("component6()")));
- (Ssi_agentIndyCredentialDefinition *)doCopyVer:(NSString *)ver id:(NSString *)id schemaSeqNo:(NSString *)schemaSeqNo type:(NSString *)type tag:(NSString *)tag value:(Ssi_agentIndyCredentialPubKeys *)value __attribute__((swift_name("doCopy(ver:id:schemaSeqNo:type:tag:value:)")));
- (BOOL)isEqual:(id _Nullable)other __attribute__((swift_name("isEqual(_:)")));
- (NSUInteger)hash __attribute__((swift_name("hash()")));
- (NSString *)description __attribute__((swift_name("description()")));
@property (readonly) NSString *id __attribute__((swift_name("id")));
@property (readonly) NSString *schemaSeqNo __attribute__((swift_name("schemaSeqNo")));
@property (readonly) NSString *tag __attribute__((swift_name("tag")));
@property (readonly) NSString *type __attribute__((swift_name("type")));
@property (readonly) Ssi_agentIndyCredentialPubKeys *value __attribute__((swift_name("value")));
@property (readonly) NSString *ver __attribute__((swift_name("ver")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("IndyCredentialDefinition.Companion")))
@interface Ssi_agentIndyCredentialDefinitionCompanion : Ssi_agentBase
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
+ (instancetype)companion __attribute__((swift_name("init()")));
- (id<Ssi_agentKotlinx_serialization_coreKSerializer>)serializer __attribute__((swift_name("serializer()")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("IndyCredentialDefinitionId")))
@interface Ssi_agentIndyCredentialDefinitionId : Ssi_agentBase <Ssi_agentCredentialDefinitionId>
- (instancetype)initWithDid:(NSString *)did schemaSeqNo:(int32_t)schemaSeqNo tag:(NSString *)tag __attribute__((swift_name("init(did:schemaSeqNo:tag:)"))) __attribute__((objc_designated_initializer));
- (NSString *)component1 __attribute__((swift_name("component1()")));
- (int32_t)component2 __attribute__((swift_name("component2()")));
- (NSString *)component3 __attribute__((swift_name("component3()")));
- (Ssi_agentIndyCredentialDefinitionId *)doCopyDid:(NSString *)did schemaSeqNo:(int32_t)schemaSeqNo tag:(NSString *)tag __attribute__((swift_name("doCopy(did:schemaSeqNo:tag:)")));
- (BOOL)isEqual:(id _Nullable)other __attribute__((swift_name("isEqual(_:)")));
- (Ssi_agentRevocationRegistryDefinitionId *)getPossibleRevocationRegistryDefinitionIdRevTag:(NSString *)revTag __attribute__((swift_name("getPossibleRevocationRegistryDefinitionId(revTag:)")));
- (NSUInteger)hash __attribute__((swift_name("hash()")));
- (NSString *)description __attribute__((swift_name("description()")));
@property (readonly) NSString *did __attribute__((swift_name("did")));
@property (readonly) int32_t schemaSeqNo __attribute__((swift_name("schemaSeqNo")));
@property (readonly) NSString *tag __attribute__((swift_name("tag")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("IndyCredentialDefinitionId.Companion")))
@interface Ssi_agentIndyCredentialDefinitionIdCompanion : Ssi_agentBase <Ssi_agentFromString>
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
+ (instancetype)companion __attribute__((swift_name("init()")));
- (Ssi_agentIndyCredentialDefinitionId *)fromStringStr:(NSString *)str __attribute__((swift_name("fromString(str:)")));
- (id<Ssi_agentKotlinx_serialization_coreKSerializer>)serializer __attribute__((swift_name("serializer()")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("IndyCredentialOffer")))
@interface Ssi_agentIndyCredentialOffer : Ssi_agentBase <Ssi_agentCredentialOffer>
- (instancetype)initWithSchemaIdRaw:(NSString *)schemaIdRaw credentialDefinitionIdRaw:(NSString *)credentialDefinitionIdRaw keyCorrectnessProof:(Ssi_agentIndyKeyCorrectnessProof *)keyCorrectnessProof nonce:(NSString *)nonce __attribute__((swift_name("init(schemaIdRaw:credentialDefinitionIdRaw:keyCorrectnessProof:nonce:)"))) __attribute__((objc_designated_initializer));
- (NSString *)component1 __attribute__((swift_name("component1()")));
- (NSString *)component2 __attribute__((swift_name("component2()")));
- (Ssi_agentIndyKeyCorrectnessProof *)component3 __attribute__((swift_name("component3()")));
- (NSString *)component4 __attribute__((swift_name("component4()")));
- (Ssi_agentIndyCredentialOffer *)doCopySchemaIdRaw:(NSString *)schemaIdRaw credentialDefinitionIdRaw:(NSString *)credentialDefinitionIdRaw keyCorrectnessProof:(Ssi_agentIndyKeyCorrectnessProof *)keyCorrectnessProof nonce:(NSString *)nonce __attribute__((swift_name("doCopy(schemaIdRaw:credentialDefinitionIdRaw:keyCorrectnessProof:nonce:)")));
- (BOOL)isEqual:(id _Nullable)other __attribute__((swift_name("isEqual(_:)")));
- (NSUInteger)hash __attribute__((swift_name("hash()")));
- (NSString *)description __attribute__((swift_name("description()")));
@property (readonly) NSString *credentialDefinitionIdRaw __attribute__((swift_name("credentialDefinitionIdRaw")));
@property (readonly) Ssi_agentIndyKeyCorrectnessProof *keyCorrectnessProof __attribute__((swift_name("keyCorrectnessProof")));
@property (readonly) NSString *nonce __attribute__((swift_name("nonce")));
@property (readonly) NSString *schemaIdRaw __attribute__((swift_name("schemaIdRaw")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("IndyCredentialOffer.Companion")))
@interface Ssi_agentIndyCredentialOfferCompanion : Ssi_agentBase
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
+ (instancetype)companion __attribute__((swift_name("init()")));
- (id<Ssi_agentKotlinx_serialization_coreKSerializer>)serializer __attribute__((swift_name("serializer()")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("IndyCredentialPubKeys")))
@interface Ssi_agentIndyCredentialPubKeys : Ssi_agentBase
- (instancetype)initWithPrimary:(NSString *)primary revocation:(NSString * _Nullable)revocation __attribute__((swift_name("init(primary:revocation:)"))) __attribute__((objc_designated_initializer));
- (NSString *)component1 __attribute__((swift_name("component1()")));
- (NSString * _Nullable)component2 __attribute__((swift_name("component2()")));
- (Ssi_agentIndyCredentialPubKeys *)doCopyPrimary:(NSString *)primary revocation:(NSString * _Nullable)revocation __attribute__((swift_name("doCopy(primary:revocation:)")));
- (BOOL)isEqual:(id _Nullable)other __attribute__((swift_name("isEqual(_:)")));
- (NSUInteger)hash __attribute__((swift_name("hash()")));
- (NSString *)description __attribute__((swift_name("description()")));
@property (readonly) NSString *primary __attribute__((swift_name("primary")));
@property (readonly) NSString * _Nullable revocation __attribute__((swift_name("revocation")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("IndyCredentialPubKeys.Companion")))
@interface Ssi_agentIndyCredentialPubKeysCompanion : Ssi_agentBase
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
+ (instancetype)companion __attribute__((swift_name("init()")));
- (id<Ssi_agentKotlinx_serialization_coreKSerializer>)serializer __attribute__((swift_name("serializer()")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("IndyCredentialRequest")))
@interface Ssi_agentIndyCredentialRequest : Ssi_agentBase <Ssi_agentCredentialRequest>
- (instancetype)initWithProverDid:(NSString *)proverDid credentialDefinitionIdRaw:(NSString *)credentialDefinitionIdRaw blindedMs:(NSString *)blindedMs blindedMsCorrectnessProof:(NSString *)blindedMsCorrectnessProof nonce:(NSString *)nonce __attribute__((swift_name("init(proverDid:credentialDefinitionIdRaw:blindedMs:blindedMsCorrectnessProof:nonce:)"))) __attribute__((objc_designated_initializer));
- (NSString *)component1 __attribute__((swift_name("component1()")));
- (NSString *)component2 __attribute__((swift_name("component2()")));
- (NSString *)component3 __attribute__((swift_name("component3()")));
- (NSString *)component4 __attribute__((swift_name("component4()")));
- (NSString *)component5 __attribute__((swift_name("component5()")));
- (Ssi_agentIndyCredentialRequest *)doCopyProverDid:(NSString *)proverDid credentialDefinitionIdRaw:(NSString *)credentialDefinitionIdRaw blindedMs:(NSString *)blindedMs blindedMsCorrectnessProof:(NSString *)blindedMsCorrectnessProof nonce:(NSString *)nonce __attribute__((swift_name("doCopy(proverDid:credentialDefinitionIdRaw:blindedMs:blindedMsCorrectnessProof:nonce:)")));
- (BOOL)isEqual:(id _Nullable)other __attribute__((swift_name("isEqual(_:)")));
- (NSUInteger)hash __attribute__((swift_name("hash()")));
- (NSString *)description __attribute__((swift_name("description()")));
@property (readonly) NSString *blindedMs __attribute__((swift_name("blindedMs")));
@property (readonly) NSString *blindedMsCorrectnessProof __attribute__((swift_name("blindedMsCorrectnessProof")));
@property (readonly) NSString *credentialDefinitionIdRaw __attribute__((swift_name("credentialDefinitionIdRaw")));
@property (readonly) NSString *nonce __attribute__((swift_name("nonce")));
@property (readonly) NSString *proverDid __attribute__((swift_name("proverDid")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("IndyCredentialRequest.Companion")))
@interface Ssi_agentIndyCredentialRequestCompanion : Ssi_agentBase
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
+ (instancetype)companion __attribute__((swift_name("init()")));
- (id<Ssi_agentKotlinx_serialization_coreKSerializer>)serializer __attribute__((swift_name("serializer()")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("IndyCredentialRequestMetadata")))
@interface Ssi_agentIndyCredentialRequestMetadata : Ssi_agentBase <Ssi_agentCredentialRequestMetadata>
- (instancetype)initWithMasterSecretBlindingData:(NSString *)masterSecretBlindingData masterSecretName:(NSString *)masterSecretName nonce:(NSString *)nonce __attribute__((swift_name("init(masterSecretBlindingData:masterSecretName:nonce:)"))) __attribute__((objc_designated_initializer));
- (NSString *)component1 __attribute__((swift_name("component1()")));
- (NSString *)component2 __attribute__((swift_name("component2()")));
- (NSString *)component3 __attribute__((swift_name("component3()")));
- (Ssi_agentIndyCredentialRequestMetadata *)doCopyMasterSecretBlindingData:(NSString *)masterSecretBlindingData masterSecretName:(NSString *)masterSecretName nonce:(NSString *)nonce __attribute__((swift_name("doCopy(masterSecretBlindingData:masterSecretName:nonce:)")));
- (BOOL)isEqual:(id _Nullable)other __attribute__((swift_name("isEqual(_:)")));
- (NSUInteger)hash __attribute__((swift_name("hash()")));
- (NSString *)description __attribute__((swift_name("description()")));
@property (readonly) NSString *masterSecretBlindingData __attribute__((swift_name("masterSecretBlindingData")));
@property (readonly) NSString *masterSecretName __attribute__((swift_name("masterSecretName")));
@property (readonly) NSString *nonce __attribute__((swift_name("nonce")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("IndyCredentialRequestMetadata.Companion")))
@interface Ssi_agentIndyCredentialRequestMetadataCompanion : Ssi_agentBase
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
+ (instancetype)companion __attribute__((swift_name("init()")));
- (id<Ssi_agentKotlinx_serialization_coreKSerializer>)serializer __attribute__((swift_name("serializer()")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("IndyKeyCorrectnessProof")))
@interface Ssi_agentIndyKeyCorrectnessProof : Ssi_agentBase
- (instancetype)initWithC:(NSString *)c xzCap:(NSString *)xzCap xrCap:(NSArray<NSArray<NSString *> *> *)xrCap __attribute__((swift_name("init(c:xzCap:xrCap:)"))) __attribute__((objc_designated_initializer));
- (NSString *)component1 __attribute__((swift_name("component1()")));
- (NSString *)component2 __attribute__((swift_name("component2()")));
- (NSArray<NSArray<NSString *> *> *)component3 __attribute__((swift_name("component3()")));
- (Ssi_agentIndyKeyCorrectnessProof *)doCopyC:(NSString *)c xzCap:(NSString *)xzCap xrCap:(NSArray<NSArray<NSString *> *> *)xrCap __attribute__((swift_name("doCopy(c:xzCap:xrCap:)")));
- (BOOL)isEqual:(id _Nullable)other __attribute__((swift_name("isEqual(_:)")));
- (NSUInteger)hash __attribute__((swift_name("hash()")));
- (NSString *)description __attribute__((swift_name("description()")));
@property (readonly) NSString *c __attribute__((swift_name("c")));
@property (readonly) NSArray<NSArray<NSString *> *> *xrCap __attribute__((swift_name("xrCap")));
@property (readonly) NSString *xzCap __attribute__((swift_name("xzCap")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("IndyKeyCorrectnessProof.Companion")))
@interface Ssi_agentIndyKeyCorrectnessProofCompanion : Ssi_agentBase
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
+ (instancetype)companion __attribute__((swift_name("init()")));
- (id<Ssi_agentKotlinx_serialization_coreKSerializer>)serializer __attribute__((swift_name("serializer()")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("MasterSecretBlindingData")))
@interface Ssi_agentMasterSecretBlindingData : Ssi_agentBase
- (instancetype)initWithVPrime:(NSString *)vPrime vrPrime:(NSString * _Nullable)vrPrime __attribute__((swift_name("init(vPrime:vrPrime:)"))) __attribute__((objc_designated_initializer));
- (NSString *)component1 __attribute__((swift_name("component1()")));
- (NSString * _Nullable)component2 __attribute__((swift_name("component2()")));
- (Ssi_agentMasterSecretBlindingData *)doCopyVPrime:(NSString *)vPrime vrPrime:(NSString * _Nullable)vrPrime __attribute__((swift_name("doCopy(vPrime:vrPrime:)")));
- (BOOL)isEqual:(id _Nullable)other __attribute__((swift_name("isEqual(_:)")));
- (NSUInteger)hash __attribute__((swift_name("hash()")));
- (NSString *)description __attribute__((swift_name("description()")));
@property (readonly) NSString *vPrime __attribute__((swift_name("vPrime")));
@property (readonly) NSString * _Nullable vrPrime __attribute__((swift_name("vrPrime")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("MasterSecretBlindingData.Companion")))
@interface Ssi_agentMasterSecretBlindingDataCompanion : Ssi_agentBase
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
+ (instancetype)companion __attribute__((swift_name("init()")));
- (id<Ssi_agentKotlinx_serialization_coreKSerializer>)serializer __attribute__((swift_name("serializer()")));
@end;

__attribute__((swift_name("ContainsCredentialDefinitionId")))
@protocol Ssi_agentContainsCredentialDefinitionId
@required
- (Ssi_agentIndyCredentialDefinitionId *)getCredentialDefinitionIdObject __attribute__((swift_name("getCredentialDefinitionIdObject()")));
@property (readonly) NSString *credentialDefinitionIdRaw __attribute__((swift_name("credentialDefinitionIdRaw")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("RevocationRegistryDefinitionId")))
@interface Ssi_agentRevocationRegistryDefinitionId : Ssi_agentBase <Ssi_agentContainsCredentialDefinitionId>
- (instancetype)initWithDid:(NSString *)did credentialDefinitionIdRaw:(NSString *)credentialDefinitionIdRaw tag:(NSString *)tag __attribute__((swift_name("init(did:credentialDefinitionIdRaw:tag:)"))) __attribute__((objc_designated_initializer));
- (NSString *)component1 __attribute__((swift_name("component1()")));
- (NSString *)component2 __attribute__((swift_name("component2()")));
- (NSString *)component3 __attribute__((swift_name("component3()")));
- (Ssi_agentRevocationRegistryDefinitionId *)doCopyDid:(NSString *)did credentialDefinitionIdRaw:(NSString *)credentialDefinitionIdRaw tag:(NSString *)tag __attribute__((swift_name("doCopy(did:credentialDefinitionIdRaw:tag:)")));
- (BOOL)isEqual:(id _Nullable)other __attribute__((swift_name("isEqual(_:)")));
- (NSUInteger)hash __attribute__((swift_name("hash()")));
- (NSString *)description __attribute__((swift_name("description()")));
@property (readonly) NSString *credentialDefinitionIdRaw __attribute__((swift_name("credentialDefinitionIdRaw")));
@property (readonly) NSString *did __attribute__((swift_name("did")));
@property (readonly) NSString *tag __attribute__((swift_name("tag")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("RevocationRegistryDefinitionId.Companion")))
@interface Ssi_agentRevocationRegistryDefinitionIdCompanion : Ssi_agentBase <Ssi_agentFromString>
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
+ (instancetype)companion __attribute__((swift_name("init()")));
- (Ssi_agentRevocationRegistryDefinitionId *)fromStringStr:(NSString *)str __attribute__((swift_name("fromString(str:)")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("WalletHelper")))
@interface Ssi_agentWalletHelper : Ssi_agentBase
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
+ (instancetype)walletHelper __attribute__((swift_name("init()")));

/**
 @note This method converts instances of CancellationException to errors.
 Other uncaught Kotlin exceptions are fatal.
*/
- (void)createNonExistingConfig:(Ssi_agentWalletConfig *)config password:(Ssi_agentWalletPassword *)password completionHandler:(void (^)(Ssi_agentKotlinUnit * _Nullable, NSError * _Nullable))completionHandler __attribute__((swift_name("createNonExisting(config:password:completionHandler:)")));

/**
 @note This method converts instances of CancellationException to errors.
 Other uncaught Kotlin exceptions are fatal.
*/
- (void)createNonExistingWalletName:(NSString *)walletName walletPassword:(NSString *)walletPassword completionHandler:(void (^)(Ssi_agentKotlinUnit * _Nullable, NSError * _Nullable))completionHandler __attribute__((swift_name("createNonExisting(walletName:walletPassword:completionHandler:)")));

/**
 @note This method converts instances of CancellationException to errors.
 Other uncaught Kotlin exceptions are fatal.
*/
- (void)createOrTruncConfig:(Ssi_agentWalletConfig *)config password:(Ssi_agentWalletPassword *)password completionHandler:(void (^)(Ssi_agentKotlinUnit * _Nullable, NSError * _Nullable))completionHandler __attribute__((swift_name("createOrTrunc(config:password:completionHandler:)")));

/**
 @note This method converts instances of CancellationException to errors.
 Other uncaught Kotlin exceptions are fatal.
*/
- (void)createOrTruncWalletName:(NSString *)walletName walletPassword:(NSString *)walletPassword completionHandler:(void (^)(Ssi_agentKotlinUnit * _Nullable, NSError * _Nullable))completionHandler __attribute__((swift_name("createOrTrunc(walletName:walletPassword:completionHandler:)")));
- (BOOL)existsWalletName:(NSString *)walletName __attribute__((swift_name("exists(walletName:)")));

/**
 @note This method converts instances of CancellationException to errors.
 Other uncaught Kotlin exceptions are fatal.
*/
- (void)openExistingConfig:(Ssi_agentWalletConfig *)config password:(Ssi_agentWalletPassword *)password completionHandler:(void (^)(Ssi_agentWallet * _Nullable, NSError * _Nullable))completionHandler __attribute__((swift_name("openExisting(config:password:completionHandler:)")));

/**
 @note This method converts instances of CancellationException to errors.
 Other uncaught Kotlin exceptions are fatal.
*/
- (void)openExistingWalletName:(NSString *)walletName walletPassword:(NSString *)walletPassword completionHandler:(void (^)(Ssi_agentWallet * _Nullable, NSError * _Nullable))completionHandler __attribute__((swift_name("openExisting(walletName:walletPassword:completionHandler:)")));

/**
 @note This method converts instances of CancellationException to errors.
 Other uncaught Kotlin exceptions are fatal.
*/
- (void)openOrCreateConfig:(Ssi_agentWalletConfig *)config password:(Ssi_agentWalletPassword *)password completionHandler:(void (^)(Ssi_agentWallet * _Nullable, NSError * _Nullable))completionHandler __attribute__((swift_name("openOrCreate(config:password:completionHandler:)")));

/**
 @note This method converts instances of CancellationException to errors.
 Other uncaught Kotlin exceptions are fatal.
*/
- (void)openOrCreateWalletName:(NSString *)walletName walletPassword:(NSString *)walletPassword completionHandler:(void (^)(Ssi_agentWallet * _Nullable, NSError * _Nullable))completionHandler __attribute__((swift_name("openOrCreate(walletName:walletPassword:completionHandler:)")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("Anoncreds")))
@interface Ssi_agentAnoncreds : Ssi_agentBase
- (instancetype)init __attribute__((swift_name("init()"))) __attribute__((objc_designated_initializer));
+ (instancetype)new __attribute__((availability(swift, unavailable, message="use object initializers instead")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("Anoncreds.Companion")))
@interface Ssi_agentAnoncredsCompanion : Ssi_agentBase
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
+ (instancetype)companion __attribute__((swift_name("init()")));

/**
 @note This method converts instances of CancellationException to errors.
 Other uncaught Kotlin exceptions are fatal.
*/
- (void)createRevocationStateBlobStorageReaderHandle:(int32_t)blobStorageReaderHandle revRegDef:(NSString *)revRegDef revRegDelta:(NSString *)revRegDelta timestamp:(int64_t)timestamp credRevId:(NSString *)credRevId completionHandler:(void (^)(NSString * _Nullable, NSError * _Nullable))completionHandler __attribute__((swift_name("createRevocationState(blobStorageReaderHandle:revRegDef:revRegDelta:timestamp:credRevId:completionHandler:)")));

/**
 @note This method converts instances of CancellationException to errors.
 Other uncaught Kotlin exceptions are fatal.
*/
- (void)proverCreateCredentialReqWallet:(Ssi_agentWallet *)wallet proverDid:(NSString *)proverDid credentialOfferJson:(NSString *)credentialOfferJson credentialDefJson:(NSString *)credentialDefJson masterSecretId:(NSString *)masterSecretId completionHandler:(void (^)(Ssi_agentProverCreateCredentialRequestResult * _Nullable, NSError * _Nullable))completionHandler __attribute__((swift_name("proverCreateCredentialReq(wallet:proverDid:credentialOfferJson:credentialDefJson:masterSecretId:completionHandler:)")));

/**
 @note This method converts instances of CancellationException to errors.
 Other uncaught Kotlin exceptions are fatal.
*/
- (void)proverCreateMasterSecretWallet:(Ssi_agentWallet *)wallet masterSecretId:(NSString *)masterSecretId completionHandler:(void (^)(NSString * _Nullable, NSError * _Nullable))completionHandler __attribute__((swift_name("proverCreateMasterSecret(wallet:masterSecretId:completionHandler:)")));

/**
 @note This method converts instances of CancellationException to errors.
 Other uncaught Kotlin exceptions are fatal.
*/
- (void)proverCreateProofWallet:(Ssi_agentWallet *)wallet proofRequest:(NSString *)proofRequest requestedCredentials:(NSString *)requestedCredentials masterSecret:(NSString * _Nullable)masterSecret schemas:(NSString *)schemas credentialDefs:(NSString *)credentialDefs revStates:(NSString *)revStates completionHandler:(void (^)(NSString * _Nullable, NSError * _Nullable))completionHandler __attribute__((swift_name("proverCreateProof(wallet:proofRequest:requestedCredentials:masterSecret:schemas:credentialDefs:revStates:completionHandler:)")));

/**
 @note This method converts instances of CancellationException to errors.
 Other uncaught Kotlin exceptions are fatal.
*/
- (void)proverStoreCredentialWallet:(Ssi_agentWallet *)wallet credId:(NSString * _Nullable)credId credReqMetadataJson:(NSString *)credReqMetadataJson credJson:(NSString *)credJson credDefJson:(NSString *)credDefJson revRegDefJson:(NSString * _Nullable)revRegDefJson completionHandler:(void (^)(NSString * _Nullable, NSError * _Nullable))completionHandler __attribute__((swift_name("proverStoreCredential(wallet:credId:credReqMetadataJson:credJson:credDefJson:revRegDefJson:completionHandler:)")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("Anoncreds.ProverCreateCredentialReqCallbackResult")))
@interface Ssi_agentAnoncredsProverCreateCredentialReqCallbackResult : Ssi_agentBase <Ssi_agentCallbackData>
- (instancetype)initWithCommandHandle:(int32_t)commandHandle errorCode:(uint32_t)errorCode credReqJson:(NSString *)credReqJson credReqMetadataJson:(NSString *)credReqMetadataJson __attribute__((swift_name("init(commandHandle:errorCode:credReqJson:credReqMetadataJson:)"))) __attribute__((objc_designated_initializer));
- (int32_t)component1 __attribute__((swift_name("component1()")));
- (uint32_t)component2 __attribute__((swift_name("component2()")));
- (NSString *)component3 __attribute__((swift_name("component3()")));
- (NSString *)component4 __attribute__((swift_name("component4()")));
- (Ssi_agentAnoncredsProverCreateCredentialReqCallbackResult *)doCopyCommandHandle:(int32_t)commandHandle errorCode:(uint32_t)errorCode credReqJson:(NSString *)credReqJson credReqMetadataJson:(NSString *)credReqMetadataJson __attribute__((swift_name("doCopy(commandHandle:errorCode:credReqJson:credReqMetadataJson:)")));
- (BOOL)isEqual:(id _Nullable)other __attribute__((swift_name("isEqual(_:)")));
- (NSUInteger)hash __attribute__((swift_name("hash()")));
- (NSString *)description __attribute__((swift_name("description()")));
@property (readonly) int32_t commandHandle __attribute__((swift_name("commandHandle")));
@property (readonly) NSString *credReqJson __attribute__((swift_name("credReqJson")));
@property (readonly) NSString *credReqMetadataJson __attribute__((swift_name("credReqMetadataJson")));
@property (readonly) uint32_t errorCode __attribute__((swift_name("errorCode")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("CreateAndStoreMyDidResult")))
@interface Ssi_agentCreateAndStoreMyDidResult : Ssi_agentBase
- (instancetype)initWithDid:(NSString *)Did VerKey:(NSString *)VerKey __attribute__((swift_name("init(Did:VerKey:)"))) __attribute__((objc_designated_initializer));
- (NSString *)getDid __attribute__((swift_name("getDid()")));
- (NSString *)getVerkey __attribute__((swift_name("getVerkey()")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("CredentialsSearchForProofReq")))
@interface Ssi_agentCredentialsSearchForProofReq : Ssi_agentBase
- (instancetype)init __attribute__((swift_name("init()"))) __attribute__((objc_designated_initializer));
+ (instancetype)new __attribute__((availability(swift, unavailable, message="use object initializers instead")));

/**
 @note This method converts instances of CancellationException to errors.
 Other uncaught Kotlin exceptions are fatal.
*/
- (void)closeSearchWithCompletionHandler:(void (^)(Ssi_agentKotlinUnit * _Nullable, NSError * _Nullable))completionHandler __attribute__((swift_name("closeSearch(completionHandler:)")));

/**
 @note This method converts instances of CancellationException to errors.
 Other uncaught Kotlin exceptions are fatal.
*/
- (void)fetchNextCredentialsItemRef:(NSString *)itemRef count:(int32_t)count completionHandler:(void (^)(NSString * _Nullable, NSError * _Nullable))completionHandler __attribute__((swift_name("fetchNextCredentials(itemRef:count:completionHandler:)")));

/**
 @note This method converts instances of CancellationException to errors.
 Other uncaught Kotlin exceptions are fatal.
*/
- (void)openWallet:(Ssi_agentWallet *)wallet proofReqJson:(NSString *)proofReqJson extraQueryJson:(NSString * _Nullable)extraQueryJson completionHandler:(void (^)(Ssi_agentKotlinUnit * _Nullable, NSError * _Nullable))completionHandler __attribute__((swift_name("open(wallet:proofReqJson:extraQueryJson:completionHandler:)")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("Crypto")))
@interface Ssi_agentCrypto : Ssi_agentBase
- (instancetype)init __attribute__((swift_name("init()"))) __attribute__((objc_designated_initializer));
+ (instancetype)new __attribute__((availability(swift, unavailable, message="use object initializers instead")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("Crypto.Companion")))
@interface Ssi_agentCryptoCompanion : Ssi_agentBase
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
+ (instancetype)companion __attribute__((swift_name("init()")));

/**
 @note This method converts instances of CancellationException to errors.
 Other uncaught Kotlin exceptions are fatal.
*/
- (void)packMessageWallet:(Ssi_agentWallet *)wallet recipientVk:(NSString *)recipientVk senderVk:(NSString * _Nullable)senderVk message:(Ssi_agentKotlinByteArray *)message completionHandler:(void (^)(Ssi_agentKotlinByteArray * _Nullable, NSError * _Nullable))completionHandler __attribute__((swift_name("packMessage(wallet:recipientVk:senderVk:message:completionHandler:)")));

/**
 @note This method converts instances of CancellationException to errors.
 Other uncaught Kotlin exceptions are fatal.
*/
- (void)unpackMessageWallet:(Ssi_agentWallet *)wallet jwe_data:(Ssi_agentKotlinByteArray *)jwe_data completionHandler:(void (^)(Ssi_agentKotlinByteArray * _Nullable, NSError * _Nullable))completionHandler __attribute__((swift_name("unpackMessage(wallet:jwe_data:completionHandler:)")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("Crypto.PackedMessageCallbackResult")))
@interface Ssi_agentCryptoPackedMessageCallbackResult : Ssi_agentBase <Ssi_agentCallbackData>
- (instancetype)initWithCommandHandle:(int32_t)commandHandle errorCode:(uint32_t)errorCode packedMessage:(Ssi_agentKotlinByteArray *)packedMessage __attribute__((swift_name("init(commandHandle:errorCode:packedMessage:)"))) __attribute__((objc_designated_initializer));
- (int32_t)component1 __attribute__((swift_name("component1()")));
- (uint32_t)component2 __attribute__((swift_name("component2()")));
- (Ssi_agentKotlinByteArray *)component3 __attribute__((swift_name("component3()")));
- (Ssi_agentCryptoPackedMessageCallbackResult *)doCopyCommandHandle:(int32_t)commandHandle errorCode:(uint32_t)errorCode packedMessage:(Ssi_agentKotlinByteArray *)packedMessage __attribute__((swift_name("doCopy(commandHandle:errorCode:packedMessage:)")));
- (BOOL)isEqual:(id _Nullable)other __attribute__((swift_name("isEqual(_:)")));
- (NSUInteger)hash __attribute__((swift_name("hash()")));
- (NSString *)description __attribute__((swift_name("description()")));
@property (readonly) int32_t commandHandle __attribute__((swift_name("commandHandle")));
@property (readonly) uint32_t errorCode __attribute__((swift_name("errorCode")));
@property (readonly) Ssi_agentKotlinByteArray *packedMessage __attribute__((swift_name("packedMessage")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("Crypto.UnPackedMessageCallbackResult")))
@interface Ssi_agentCryptoUnPackedMessageCallbackResult : Ssi_agentBase <Ssi_agentCallbackData>
- (instancetype)initWithCommandHandle:(int32_t)commandHandle errorCode:(uint32_t)errorCode unPackedMessage:(Ssi_agentKotlinByteArray *)unPackedMessage __attribute__((swift_name("init(commandHandle:errorCode:unPackedMessage:)"))) __attribute__((objc_designated_initializer));
- (int32_t)component1 __attribute__((swift_name("component1()")));
- (uint32_t)component2 __attribute__((swift_name("component2()")));
- (Ssi_agentKotlinByteArray *)component3 __attribute__((swift_name("component3()")));
- (Ssi_agentCryptoUnPackedMessageCallbackResult *)doCopyCommandHandle:(int32_t)commandHandle errorCode:(uint32_t)errorCode unPackedMessage:(Ssi_agentKotlinByteArray *)unPackedMessage __attribute__((swift_name("doCopy(commandHandle:errorCode:unPackedMessage:)")));
- (BOOL)isEqual:(id _Nullable)other __attribute__((swift_name("isEqual(_:)")));
- (NSUInteger)hash __attribute__((swift_name("hash()")));
- (NSString *)description __attribute__((swift_name("description()")));
@property (readonly) int32_t commandHandle __attribute__((swift_name("commandHandle")));
@property (readonly) uint32_t errorCode __attribute__((swift_name("errorCode")));
@property (readonly) Ssi_agentKotlinByteArray *unPackedMessage __attribute__((swift_name("unPackedMessage")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("Did")))
@interface Ssi_agentDid : Ssi_agentBase
- (instancetype)init __attribute__((swift_name("init()"))) __attribute__((objc_designated_initializer));
+ (instancetype)new __attribute__((availability(swift, unavailable, message="use object initializers instead")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("Did.Companion")))
@interface Ssi_agentDidCompanion : Ssi_agentBase
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
+ (instancetype)companion __attribute__((swift_name("init()")));

/**
 @note This method converts instances of CancellationException to errors.
 Other uncaught Kotlin exceptions are fatal.
*/
- (void)createAndStoreMyDidWallet:(Ssi_agentWallet *)wallet didJson:(NSString *)didJson completionHandler:(void (^)(Ssi_agentCreateAndStoreMyDidResult * _Nullable, NSError * _Nullable))completionHandler __attribute__((swift_name("createAndStoreMyDid(wallet:didJson:completionHandler:)")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("Did.CreateAndStoreMyDidCallbackResult")))
@interface Ssi_agentDidCreateAndStoreMyDidCallbackResult : Ssi_agentBase <Ssi_agentCallbackData>
- (instancetype)initWithCommandHandle:(int32_t)commandHandle errorCode:(uint32_t)errorCode did:(NSString *)did verkey:(NSString *)verkey __attribute__((swift_name("init(commandHandle:errorCode:did:verkey:)"))) __attribute__((objc_designated_initializer));
- (int32_t)component1 __attribute__((swift_name("component1()")));
- (uint32_t)component2 __attribute__((swift_name("component2()")));
- (NSString *)component3 __attribute__((swift_name("component3()")));
- (NSString *)component4 __attribute__((swift_name("component4()")));
- (Ssi_agentDidCreateAndStoreMyDidCallbackResult *)doCopyCommandHandle:(int32_t)commandHandle errorCode:(uint32_t)errorCode did:(NSString *)did verkey:(NSString *)verkey __attribute__((swift_name("doCopy(commandHandle:errorCode:did:verkey:)")));
- (BOOL)isEqual:(id _Nullable)other __attribute__((swift_name("isEqual(_:)")));
- (NSUInteger)hash __attribute__((swift_name("hash()")));
- (NSString *)description __attribute__((swift_name("description()")));
@property (readonly) int32_t commandHandle __attribute__((swift_name("commandHandle")));
@property (readonly) NSString *did __attribute__((swift_name("did")));
@property (readonly) uint32_t errorCode __attribute__((swift_name("errorCode")));
@property (readonly) NSString *verkey __attribute__((swift_name("verkey")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("ProverCreateCredentialRequestResult")))
@interface Ssi_agentProverCreateCredentialRequestResult : Ssi_agentBase
- (instancetype)initWithCredentialRequestJson:(NSString *)credentialRequestJson credentialRequestMetadataJson:(NSString *)credentialRequestMetadataJson __attribute__((swift_name("init(credentialRequestJson:credentialRequestMetadataJson:)"))) __attribute__((objc_designated_initializer));
- (NSString *)getCredentialRequestJson __attribute__((swift_name("getCredentialRequestJson()")));
- (NSString *)getCredentialRequestMetadataJson __attribute__((swift_name("getCredentialRequestMetadataJson()")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("Wallet")))
@interface Ssi_agentWallet : Ssi_agentBase
- (instancetype)initWithWalletHandle:(int32_t)walletHandle __attribute__((swift_name("init(walletHandle:)"))) __attribute__((objc_designated_initializer));
- (int32_t)getWalletHandle __attribute__((swift_name("getWalletHandle()")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("Wallet.Companion")))
@interface Ssi_agentWalletCompanion : Ssi_agentBase
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
+ (instancetype)companion __attribute__((swift_name("init()")));

/**
 @note This method converts instances of CancellationException to errors.
 Other uncaught Kotlin exceptions are fatal.
*/
- (void)createWalletConfig:(NSString *)config credentials:(NSString *)credentials completionHandler:(void (^)(Ssi_agentKotlinUnit * _Nullable, NSError * _Nullable))completionHandler __attribute__((swift_name("createWallet(config:credentials:completionHandler:)")));

/**
 @note This method converts instances of CancellationException to errors.
 Other uncaught Kotlin exceptions are fatal.
*/
- (void)openWalletConfig:(NSString *)config credentials:(NSString *)credentials completionHandler:(void (^)(Ssi_agentWallet * _Nullable, NSError * _Nullable))completionHandler __attribute__((swift_name("openWallet(config:credentials:completionHandler:)")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("WalletRecord")))
@interface Ssi_agentWalletRecord : Ssi_agentBase
- (instancetype)init __attribute__((swift_name("init()"))) __attribute__((objc_designated_initializer));
+ (instancetype)new __attribute__((availability(swift, unavailable, message="use object initializers instead")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("WalletRecord.Companion")))
@interface Ssi_agentWalletRecordCompanion : Ssi_agentBase
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
+ (instancetype)companion __attribute__((swift_name("init()")));

/**
 @note This method converts instances of CancellationException to errors.
 Other uncaught Kotlin exceptions are fatal.
*/
- (void)addWallet:(Ssi_agentWallet *)wallet type:(NSString *)type id:(NSString *)id value:(NSString *)value tagsJson:(NSString * _Nullable)tagsJson completionHandler:(void (^)(Ssi_agentKotlinUnit * _Nullable, NSError * _Nullable))completionHandler __attribute__((swift_name("add(wallet:type:id:value:tagsJson:completionHandler:)")));

/**
 @note This method converts instances of CancellationException to errors.
 Other uncaught Kotlin exceptions are fatal.
*/
- (void)deleteWallet:(Ssi_agentWallet *)wallet type:(NSString *)type id:(NSString *)id completionHandler:(void (^)(Ssi_agentKotlinUnit * _Nullable, NSError * _Nullable))completionHandler __attribute__((swift_name("delete(wallet:type:id:completionHandler:)")));

/**
 @note This method converts instances of CancellationException to errors.
 Other uncaught Kotlin exceptions are fatal.
*/
- (void)getWallet:(Ssi_agentWallet *)wallet type:(NSString *)type id:(NSString *)id optionsJson:(NSString *)optionsJson completionHandler:(void (^)(NSString * _Nullable, NSError * _Nullable))completionHandler __attribute__((swift_name("get(wallet:type:id:optionsJson:completionHandler:)")));

/**
 @note This method converts instances of CancellationException to errors.
 Other uncaught Kotlin exceptions are fatal.
*/
- (void)updateTagsWallet:(Ssi_agentWallet *)wallet type:(NSString *)type id:(NSString *)id tagsJson:(NSString *)tagsJson completionHandler:(void (^)(Ssi_agentKotlinUnit * _Nullable, NSError * _Nullable))completionHandler __attribute__((swift_name("updateTags(wallet:type:id:tagsJson:completionHandler:)")));

/**
 @note This method converts instances of CancellationException to errors.
 Other uncaught Kotlin exceptions are fatal.
*/
- (void)updateValueWallet:(Ssi_agentWallet *)wallet type:(NSString *)type id:(NSString *)id value:(NSString *)value completionHandler:(void (^)(Ssi_agentKotlinUnit * _Nullable, NSError * _Nullable))completionHandler __attribute__((swift_name("updateValue(wallet:type:id:value:completionHandler:)")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("WalletSearch")))
@interface Ssi_agentWalletSearch : Ssi_agentBase
- (instancetype)init __attribute__((swift_name("init()"))) __attribute__((objc_designated_initializer));
+ (instancetype)new __attribute__((availability(swift, unavailable, message="use object initializers instead")));

/**
 @note This method converts instances of CancellationException to errors.
 Other uncaught Kotlin exceptions are fatal.
*/
- (void)closeSearchWithCompletionHandler:(void (^)(Ssi_agentKotlinUnit * _Nullable, NSError * _Nullable))completionHandler __attribute__((swift_name("closeSearch(completionHandler:)")));

/**
 @note This method converts instances of CancellationException to errors.
 Other uncaught Kotlin exceptions are fatal.
*/
- (void)openWallet:(Ssi_agentWallet *)wallet type:(NSString *)type queryJson:(NSString *)queryJson optionsJson:(NSString *)optionsJson completionHandler:(void (^)(Ssi_agentKotlinUnit * _Nullable, NSError * _Nullable))completionHandler __attribute__((swift_name("open(wallet:type:queryJson:optionsJson:completionHandler:)")));

/**
 @note This method converts instances of CancellationException to errors.
 Other uncaught Kotlin exceptions are fatal.
*/
- (void)searchFetchNextRecordsWallet:(Ssi_agentWallet *)wallet count:(int32_t)count completionHandler:(void (^)(NSString * _Nullable, NSError * _Nullable))completionHandler __attribute__((swift_name("searchFetchNextRecords(wallet:count:completionHandler:)")));
@property Ssi_agentInt * _Nullable searchHandle __attribute__((swift_name("searchHandle")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("Configuration")))
@interface Ssi_agentConfiguration : Ssi_agentBase
- (instancetype)init __attribute__((swift_name("init()"))) __attribute__((objc_designated_initializer));
+ (instancetype)new __attribute__((availability(swift, unavailable, message="use object initializers instead")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("Configuration.Companion")))
@interface Ssi_agentConfigurationCompanion : Ssi_agentBase
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
+ (instancetype)companion __attribute__((swift_name("init()")));
@property (readonly) int64_t RETRY_DELAY_MS __attribute__((swift_name("RETRY_DELAY_MS")));
@property (readonly) int32_t RETRY_TIMES __attribute__((swift_name("RETRY_TIMES")));
@property (readonly) NSString *masterSecretId __attribute__((swift_name("masterSecretId")));
@property (readonly) NSString *tailsPath __attribute__((swift_name("tailsPath")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("Base64")))
@interface Ssi_agentBase64 : Ssi_agentBase
- (instancetype)init __attribute__((swift_name("init()"))) __attribute__((objc_designated_initializer));
+ (instancetype)new __attribute__((availability(swift, unavailable, message="use object initializers instead")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("Base64.Companion")))
@interface Ssi_agentBase64Companion : Ssi_agentBase
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
+ (instancetype)companion __attribute__((swift_name("init()")));
- (NSString *)base64StringToPlainStringBase64String:(NSString *)base64String __attribute__((swift_name("base64StringToPlainString(base64String:)")));
- (NSString *)plainStringToBase64StringPlainString:(NSString *)plainString __attribute__((swift_name("plainStringToBase64String(plainString:)")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("FileUtils")))
@interface Ssi_agentFileUtils : Ssi_agentBase
- (instancetype)init __attribute__((swift_name("init()"))) __attribute__((objc_designated_initializer));
+ (instancetype)new __attribute__((availability(swift, unavailable, message="use object initializers instead")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("FileUtils.Companion")))
@interface Ssi_agentFileUtilsCompanion : Ssi_agentBase
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
+ (instancetype)companion __attribute__((swift_name("init()")));
- (void)createFileWithContentFilePath:(NSString *)filePath content:(NSString *)content __attribute__((swift_name("createFileWithContent(filePath:content:)")));
- (void)deleteRecursivelyFilePath:(NSString *)filePath __attribute__((swift_name("deleteRecursively(filePath:)")));
- (BOOL)fileExistsFilePath:(NSString *)filePath __attribute__((swift_name("fileExists(filePath:)")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("Sleeper")))
@interface Ssi_agentSleeper : Ssi_agentBase
- (instancetype)init __attribute__((swift_name("init()"))) __attribute__((objc_designated_initializer));
+ (instancetype)new __attribute__((availability(swift, unavailable, message="use object initializers instead")));
- (void)sleepValue:(int32_t)value __attribute__((swift_name("sleep(value:)")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("System")))
@interface Ssi_agentSystem : Ssi_agentBase
- (instancetype)init __attribute__((swift_name("init()"))) __attribute__((objc_designated_initializer));
+ (instancetype)new __attribute__((availability(swift, unavailable, message="use object initializers instead")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("System.Companion")))
@interface Ssi_agentSystemCompanion : Ssi_agentBase
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
+ (instancetype)companion __attribute__((swift_name("init()")));
- (int64_t)currentTimeMillis __attribute__((swift_name("currentTimeMillis()")));
- (NSString * _Nullable)getEnvKey:(NSString *)key __attribute__((swift_name("getEnv(key:)")));
- (NSString * _Nullable)getPropertyKey:(NSString *)key __attribute__((swift_name("getProperty(key:)")));
- (void)setEnvKey:(NSString *)key value:(NSString *)value __attribute__((swift_name("setEnv(key:value:)")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("CallbackHandlerKt")))
@interface Ssi_agentCallbackHandlerKt : Ssi_agentBase
@property (class, readonly) Ssi_agentCallbackHandler *callbackHandler __attribute__((swift_name("callbackHandler")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("FileUtilsKt")))
@interface Ssi_agentFileUtilsKt : Ssi_agentBase
+ (NSData * _Nullable)nsdata:(NSString *)receiver __attribute__((swift_name("nsdata(_:)")));
+ (NSString * _Nullable)string:(NSData *)receiver __attribute__((swift_name("string(_:)")));
@end;

__attribute__((swift_name("KotlinIllegalStateException")))
@interface Ssi_agentKotlinIllegalStateException : Ssi_agentKotlinRuntimeException
- (instancetype)init __attribute__((swift_name("init()"))) __attribute__((objc_designated_initializer));
+ (instancetype)new __attribute__((availability(swift, unavailable, message="use object initializers instead")));
- (instancetype)initWithMessage:(NSString * _Nullable)message __attribute__((swift_name("init(message:)"))) __attribute__((objc_designated_initializer));
- (instancetype)initWithMessage:(NSString * _Nullable)message cause:(Ssi_agentKotlinThrowable * _Nullable)cause __attribute__((swift_name("init(message:cause:)"))) __attribute__((objc_designated_initializer));
- (instancetype)initWithCause:(Ssi_agentKotlinThrowable * _Nullable)cause __attribute__((swift_name("init(cause:)"))) __attribute__((objc_designated_initializer));
@end;

__attribute__((swift_name("KotlinCancellationException")))
@interface Ssi_agentKotlinCancellationException : Ssi_agentKotlinIllegalStateException
- (instancetype)init __attribute__((swift_name("init()"))) __attribute__((objc_designated_initializer));
+ (instancetype)new __attribute__((availability(swift, unavailable, message="use object initializers instead")));
- (instancetype)initWithMessage:(NSString * _Nullable)message __attribute__((swift_name("init(message:)"))) __attribute__((objc_designated_initializer));
- (instancetype)initWithMessage:(NSString * _Nullable)message cause:(Ssi_agentKotlinThrowable * _Nullable)cause __attribute__((swift_name("init(message:cause:)"))) __attribute__((objc_designated_initializer));
- (instancetype)initWithCause:(Ssi_agentKotlinThrowable * _Nullable)cause __attribute__((swift_name("init(cause:)"))) __attribute__((objc_designated_initializer));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("KotlinUnit")))
@interface Ssi_agentKotlinUnit : Ssi_agentBase
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
+ (instancetype)unit __attribute__((swift_name("init()")));
- (NSString *)description __attribute__((swift_name("description()")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("KotlinArray")))
@interface Ssi_agentKotlinArray<T> : Ssi_agentBase
+ (instancetype)arrayWithSize:(int32_t)size init:(T _Nullable (^)(Ssi_agentInt *))init __attribute__((swift_name("init(size:init:)")));
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
- (T _Nullable)getIndex:(int32_t)index __attribute__((swift_name("get(index:)")));
- (id<Ssi_agentKotlinIterator>)iterator __attribute__((swift_name("iterator()")));
- (void)setIndex:(int32_t)index value:(T _Nullable)value __attribute__((swift_name("set(index:value:)")));
@property (readonly) int32_t size __attribute__((swift_name("size")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("KotlinPair")))
@interface Ssi_agentKotlinPair<__covariant A, __covariant B> : Ssi_agentBase
- (instancetype)initWithFirst:(A _Nullable)first second:(B _Nullable)second __attribute__((swift_name("init(first:second:)"))) __attribute__((objc_designated_initializer));
- (A _Nullable)component1 __attribute__((swift_name("component1()")));
- (B _Nullable)component2 __attribute__((swift_name("component2()")));
- (Ssi_agentKotlinPair<A, B> *)doCopyFirst:(A _Nullable)first second:(B _Nullable)second __attribute__((swift_name("doCopy(first:second:)")));
- (BOOL)equalsOther:(id _Nullable)other __attribute__((swift_name("equals(other:)")));
- (int32_t)hashCode __attribute__((swift_name("hashCode()")));
- (NSString *)toString __attribute__((swift_name("toString()")));
@property (readonly) A _Nullable first __attribute__((swift_name("first")));
@property (readonly) B _Nullable second __attribute__((swift_name("second")));
@end;

__attribute__((swift_name("Kotlinx_coroutines_coreSendChannel")))
@protocol Ssi_agentKotlinx_coroutines_coreSendChannel
@required
- (BOOL)closeCause:(Ssi_agentKotlinThrowable * _Nullable)cause __attribute__((swift_name("close(cause:)")));
- (void)invokeOnCloseHandler:(void (^)(Ssi_agentKotlinThrowable * _Nullable))handler __attribute__((swift_name("invokeOnClose(handler:)")));
- (BOOL)offerElement:(id _Nullable)element __attribute__((swift_name("offer(element:)")));

/**
 @note This method converts instances of CancellationException to errors.
 Other uncaught Kotlin exceptions are fatal.
*/
- (void)sendElement:(id _Nullable)element completionHandler:(void (^)(Ssi_agentKotlinUnit * _Nullable, NSError * _Nullable))completionHandler __attribute__((swift_name("send(element:completionHandler:)")));
@property (readonly) BOOL isClosedForSend __attribute__((swift_name("isClosedForSend")));
@property (readonly) BOOL isFull __attribute__((swift_name("isFull"))) __attribute__((unavailable("Will be removed in next releases without replacement")));
@property (readonly) id<Ssi_agentKotlinx_coroutines_coreSelectClause2> onSend __attribute__((swift_name("onSend")));
@end;

__attribute__((swift_name("Kotlinx_coroutines_coreReceiveChannel")))
@protocol Ssi_agentKotlinx_coroutines_coreReceiveChannel
@required
- (void)cancelCause:(Ssi_agentKotlinx_coroutines_coreCancellationException * _Nullable)cause __attribute__((swift_name("cancel(cause:)")));
- (id<Ssi_agentKotlinx_coroutines_coreChannelIterator>)iterator __attribute__((swift_name("iterator()")));
- (id _Nullable)poll __attribute__((swift_name("poll()")));

/**
 @note This method converts instances of CancellationException to errors.
 Other uncaught Kotlin exceptions are fatal.
*/
- (void)receiveWithCompletionHandler:(void (^)(id _Nullable, NSError * _Nullable))completionHandler __attribute__((swift_name("receive(completionHandler:)")));

/**
 @note This method converts instances of CancellationException to errors.
 Other uncaught Kotlin exceptions are fatal.
*/
- (void)receiveOrClosedWithCompletionHandler:(void (^)(id _Nullable, NSError * _Nullable))completionHandler __attribute__((swift_name("receiveOrClosed(completionHandler:)")));

/**
 @note This method converts instances of CancellationException to errors.
 Other uncaught Kotlin exceptions are fatal.
*/
- (void)receiveOrNullWithCompletionHandler:(void (^)(id _Nullable, NSError * _Nullable))completionHandler __attribute__((swift_name("receiveOrNull(completionHandler:)"))) __attribute__((deprecated("Deprecated in favor of receiveOrClosed and receiveOrNull extension")));
@property (readonly) BOOL isClosedForReceive __attribute__((swift_name("isClosedForReceive")));
@property (readonly) BOOL isEmpty __attribute__((swift_name("isEmpty")));
@property (readonly) id<Ssi_agentKotlinx_coroutines_coreSelectClause1> onReceive __attribute__((swift_name("onReceive")));
@property (readonly) id<Ssi_agentKotlinx_coroutines_coreSelectClause1> onReceiveOrClosed __attribute__((swift_name("onReceiveOrClosed")));
@property (readonly) id<Ssi_agentKotlinx_coroutines_coreSelectClause1> onReceiveOrNull __attribute__((swift_name("onReceiveOrNull"))) __attribute__((deprecated("Deprecated in favor of onReceiveOrClosed and onReceiveOrNull extension")));
@end;

__attribute__((swift_name("Kotlinx_coroutines_coreChannel")))
@protocol Ssi_agentKotlinx_coroutines_coreChannel <Ssi_agentKotlinx_coroutines_coreSendChannel, Ssi_agentKotlinx_coroutines_coreReceiveChannel>
@required
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("KotlinAtomicInt")))
@interface Ssi_agentKotlinAtomicInt : Ssi_agentBase
- (instancetype)initWithValue_:(int32_t)value_ __attribute__((swift_name("init(value_:)"))) __attribute__((objc_designated_initializer));
- (int32_t)addAndGetDelta:(int32_t)delta __attribute__((swift_name("addAndGet(delta:)")));
- (BOOL)compareAndSetExpected:(int32_t)expected new:(int32_t)new_ __attribute__((swift_name("compareAndSet(expected:new:)")));
- (int32_t)compareAndSwapExpected:(int32_t)expected new:(int32_t)new_ __attribute__((swift_name("compareAndSwap(expected:new:)")));
- (void)decrement __attribute__((swift_name("decrement()")));
- (void)increment __attribute__((swift_name("increment()")));
- (NSString *)description __attribute__((swift_name("description()")));
@property int32_t value __attribute__((swift_name("value")));
@end;

__attribute__((swift_name("Kotlinx_serialization_coreEncoder")))
@protocol Ssi_agentKotlinx_serialization_coreEncoder
@required
- (id<Ssi_agentKotlinx_serialization_coreCompositeEncoder>)beginCollectionDescriptor:(id<Ssi_agentKotlinx_serialization_coreSerialDescriptor>)descriptor collectionSize:(int32_t)collectionSize __attribute__((swift_name("beginCollection(descriptor:collectionSize:)")));
- (id<Ssi_agentKotlinx_serialization_coreCompositeEncoder>)beginStructureDescriptor:(id<Ssi_agentKotlinx_serialization_coreSerialDescriptor>)descriptor __attribute__((swift_name("beginStructure(descriptor:)")));
- (void)encodeBooleanValue:(BOOL)value __attribute__((swift_name("encodeBoolean(value:)")));
- (void)encodeByteValue:(int8_t)value __attribute__((swift_name("encodeByte(value:)")));
- (void)encodeCharValue:(unichar)value __attribute__((swift_name("encodeChar(value:)")));
- (void)encodeDoubleValue:(double)value __attribute__((swift_name("encodeDouble(value:)")));
- (void)encodeEnumEnumDescriptor:(id<Ssi_agentKotlinx_serialization_coreSerialDescriptor>)enumDescriptor index:(int32_t)index __attribute__((swift_name("encodeEnum(enumDescriptor:index:)")));
- (void)encodeFloatValue:(float)value __attribute__((swift_name("encodeFloat(value:)")));
- (void)encodeIntValue:(int32_t)value __attribute__((swift_name("encodeInt(value:)")));
- (void)encodeLongValue:(int64_t)value __attribute__((swift_name("encodeLong(value:)")));
- (void)encodeNotNullMark __attribute__((swift_name("encodeNotNullMark()")));
- (void)encodeNull __attribute__((swift_name("encodeNull()")));
- (void)encodeNullableSerializableValueSerializer:(id<Ssi_agentKotlinx_serialization_coreSerializationStrategy>)serializer value:(id _Nullable)value __attribute__((swift_name("encodeNullableSerializableValue(serializer:value:)")));
- (void)encodeSerializableValueSerializer:(id<Ssi_agentKotlinx_serialization_coreSerializationStrategy>)serializer value:(id _Nullable)value __attribute__((swift_name("encodeSerializableValue(serializer:value:)")));
- (void)encodeShortValue:(int16_t)value __attribute__((swift_name("encodeShort(value:)")));
- (void)encodeStringValue:(NSString *)value __attribute__((swift_name("encodeString(value:)")));
@property (readonly) Ssi_agentKotlinx_serialization_coreSerializersModule *serializersModule __attribute__((swift_name("serializersModule")));
@end;

__attribute__((swift_name("Kotlinx_serialization_coreSerialDescriptor")))
@protocol Ssi_agentKotlinx_serialization_coreSerialDescriptor
@required
- (NSArray<id<Ssi_agentKotlinAnnotation>> *)getElementAnnotationsIndex:(int32_t)index __attribute__((swift_name("getElementAnnotations(index:)")));
- (id<Ssi_agentKotlinx_serialization_coreSerialDescriptor>)getElementDescriptorIndex:(int32_t)index __attribute__((swift_name("getElementDescriptor(index:)")));
- (int32_t)getElementIndexName:(NSString *)name __attribute__((swift_name("getElementIndex(name:)")));
- (NSString *)getElementNameIndex:(int32_t)index __attribute__((swift_name("getElementName(index:)")));
- (BOOL)isElementOptionalIndex:(int32_t)index __attribute__((swift_name("isElementOptional(index:)")));
@property (readonly) NSArray<id<Ssi_agentKotlinAnnotation>> *annotations __attribute__((swift_name("annotations")));
@property (readonly) int32_t elementsCount __attribute__((swift_name("elementsCount")));
@property (readonly) BOOL isNullable __attribute__((swift_name("isNullable")));
@property (readonly) Ssi_agentKotlinx_serialization_coreSerialKind *kind __attribute__((swift_name("kind")));
@property (readonly) NSString *serialName __attribute__((swift_name("serialName")));
@end;

__attribute__((swift_name("Kotlinx_serialization_coreDecoder")))
@protocol Ssi_agentKotlinx_serialization_coreDecoder
@required
- (id<Ssi_agentKotlinx_serialization_coreCompositeDecoder>)beginStructureDescriptor:(id<Ssi_agentKotlinx_serialization_coreSerialDescriptor>)descriptor __attribute__((swift_name("beginStructure(descriptor:)")));
- (BOOL)decodeBoolean __attribute__((swift_name("decodeBoolean()")));
- (int8_t)decodeByte __attribute__((swift_name("decodeByte()")));
- (unichar)decodeChar __attribute__((swift_name("decodeChar()")));
- (double)decodeDouble __attribute__((swift_name("decodeDouble()")));
- (int32_t)decodeEnumEnumDescriptor:(id<Ssi_agentKotlinx_serialization_coreSerialDescriptor>)enumDescriptor __attribute__((swift_name("decodeEnum(enumDescriptor:)")));
- (float)decodeFloat __attribute__((swift_name("decodeFloat()")));
- (int32_t)decodeInt __attribute__((swift_name("decodeInt()")));
- (int64_t)decodeLong __attribute__((swift_name("decodeLong()")));
- (BOOL)decodeNotNullMark __attribute__((swift_name("decodeNotNullMark()")));
- (Ssi_agentKotlinNothing * _Nullable)decodeNull __attribute__((swift_name("decodeNull()")));
- (id _Nullable)decodeNullableSerializableValueDeserializer:(id<Ssi_agentKotlinx_serialization_coreDeserializationStrategy>)deserializer __attribute__((swift_name("decodeNullableSerializableValue(deserializer:)")));
- (id _Nullable)decodeSerializableValueDeserializer:(id<Ssi_agentKotlinx_serialization_coreDeserializationStrategy>)deserializer __attribute__((swift_name("decodeSerializableValue(deserializer:)")));
- (int16_t)decodeShort __attribute__((swift_name("decodeShort()")));
- (NSString *)decodeString __attribute__((swift_name("decodeString()")));
@property (readonly) Ssi_agentKotlinx_serialization_coreSerializersModule *serializersModule __attribute__((swift_name("serializersModule")));
@end;

__attribute__((swift_name("KotlinCoroutineContext")))
@protocol Ssi_agentKotlinCoroutineContext
@required
- (id _Nullable)foldInitial:(id _Nullable)initial operation:(id _Nullable (^)(id _Nullable, id<Ssi_agentKotlinCoroutineContextElement>))operation __attribute__((swift_name("fold(initial:operation:)")));
- (id<Ssi_agentKotlinCoroutineContextElement> _Nullable)getKey:(id<Ssi_agentKotlinCoroutineContextKey>)key __attribute__((swift_name("get(key:)")));
- (id<Ssi_agentKotlinCoroutineContext>)minusKeyKey:(id<Ssi_agentKotlinCoroutineContextKey>)key __attribute__((swift_name("minusKey(key:)")));
- (id<Ssi_agentKotlinCoroutineContext>)plusContext:(id<Ssi_agentKotlinCoroutineContext>)context __attribute__((swift_name("plus(context:)")));
@end;

__attribute__((swift_name("KotlinCoroutineContextElement")))
@protocol Ssi_agentKotlinCoroutineContextElement <Ssi_agentKotlinCoroutineContext>
@required
@property (readonly) id<Ssi_agentKotlinCoroutineContextKey> key __attribute__((swift_name("key")));
@end;

__attribute__((swift_name("Kotlinx_coroutines_coreJob")))
@protocol Ssi_agentKotlinx_coroutines_coreJob <Ssi_agentKotlinCoroutineContextElement>
@required
- (id<Ssi_agentKotlinx_coroutines_coreChildHandle>)attachChildChild:(id<Ssi_agentKotlinx_coroutines_coreChildJob>)child __attribute__((swift_name("attachChild(child:)")));
- (void)cancelCause:(Ssi_agentKotlinx_coroutines_coreCancellationException * _Nullable)cause __attribute__((swift_name("cancel(cause:)")));
- (Ssi_agentKotlinx_coroutines_coreCancellationException *)getCancellationException __attribute__((swift_name("getCancellationException()")));
- (id<Ssi_agentKotlinx_coroutines_coreDisposableHandle>)invokeOnCompletionOnCancelling:(BOOL)onCancelling invokeImmediately:(BOOL)invokeImmediately handler:(void (^)(Ssi_agentKotlinThrowable * _Nullable))handler __attribute__((swift_name("invokeOnCompletion(onCancelling:invokeImmediately:handler:)")));
- (id<Ssi_agentKotlinx_coroutines_coreDisposableHandle>)invokeOnCompletionHandler:(void (^)(Ssi_agentKotlinThrowable * _Nullable))handler __attribute__((swift_name("invokeOnCompletion(handler:)")));

/**
 @note This method converts instances of CancellationException to errors.
 Other uncaught Kotlin exceptions are fatal.
*/
- (void)joinWithCompletionHandler:(void (^)(Ssi_agentKotlinUnit * _Nullable, NSError * _Nullable))completionHandler __attribute__((swift_name("join(completionHandler:)")));
- (id<Ssi_agentKotlinx_coroutines_coreJob>)plusOther:(id<Ssi_agentKotlinx_coroutines_coreJob>)other __attribute__((swift_name("plus(other:)"))) __attribute__((unavailable("Operator '+' on two Job objects is meaningless. Job is a coroutine context element and `+` is a set-sum operator for coroutine contexts. The job to the right of `+` just replaces the job the left of `+`.")));
- (BOOL)start __attribute__((swift_name("start()")));
@property (readonly) id<Ssi_agentKotlinSequence> children __attribute__((swift_name("children")));
@property (readonly) BOOL isActive __attribute__((swift_name("isActive")));
@property (readonly) BOOL isCancelled __attribute__((swift_name("isCancelled")));
@property (readonly) BOOL isCompleted __attribute__((swift_name("isCompleted")));
@property (readonly) id<Ssi_agentKotlinx_coroutines_coreSelectClause0> onJoin __attribute__((swift_name("onJoin")));
@end;

__attribute__((swift_name("Kotlinx_coroutines_coreDeferred")))
@protocol Ssi_agentKotlinx_coroutines_coreDeferred <Ssi_agentKotlinx_coroutines_coreJob>
@required

/**
 @note This method converts instances of CancellationException to errors.
 Other uncaught Kotlin exceptions are fatal.
*/
- (void)awaitWithCompletionHandler:(void (^)(id _Nullable, NSError * _Nullable))completionHandler __attribute__((swift_name("await(completionHandler:)")));
- (id _Nullable)getCompleted __attribute__((swift_name("getCompleted()")));
- (Ssi_agentKotlinThrowable * _Nullable)getCompletionExceptionOrNull __attribute__((swift_name("getCompletionExceptionOrNull()")));
@property (readonly) id<Ssi_agentKotlinx_coroutines_coreSelectClause1> onAwait __attribute__((swift_name("onAwait")));
@end;

__attribute__((swift_name("Kotlinx_serialization_coreSerialFormat")))
@protocol Ssi_agentKotlinx_serialization_coreSerialFormat
@required
@property (readonly) Ssi_agentKotlinx_serialization_coreSerializersModule *serializersModule __attribute__((swift_name("serializersModule")));
@end;

__attribute__((swift_name("Kotlinx_serialization_coreStringFormat")))
@protocol Ssi_agentKotlinx_serialization_coreStringFormat <Ssi_agentKotlinx_serialization_coreSerialFormat>
@required
- (id _Nullable)decodeFromStringDeserializer:(id<Ssi_agentKotlinx_serialization_coreDeserializationStrategy>)deserializer string:(NSString *)string __attribute__((swift_name("decodeFromString(deserializer:string:)")));
- (NSString *)encodeToStringSerializer:(id<Ssi_agentKotlinx_serialization_coreSerializationStrategy>)serializer value:(id _Nullable)value __attribute__((swift_name("encodeToString(serializer:value:)")));
@end;

__attribute__((swift_name("Kotlinx_serialization_jsonJson")))
@interface Ssi_agentKotlinx_serialization_jsonJson : Ssi_agentBase <Ssi_agentKotlinx_serialization_coreStringFormat>
- (id _Nullable)decodeFromJsonElementDeserializer:(id<Ssi_agentKotlinx_serialization_coreDeserializationStrategy>)deserializer element:(Ssi_agentKotlinx_serialization_jsonJsonElement *)element __attribute__((swift_name("decodeFromJsonElement(deserializer:element:)")));
- (id _Nullable)decodeFromStringDeserializer:(id<Ssi_agentKotlinx_serialization_coreDeserializationStrategy>)deserializer string:(NSString *)string __attribute__((swift_name("decodeFromString(deserializer:string:)")));
- (Ssi_agentKotlinx_serialization_jsonJsonElement *)encodeToJsonElementSerializer:(id<Ssi_agentKotlinx_serialization_coreSerializationStrategy>)serializer value:(id _Nullable)value __attribute__((swift_name("encodeToJsonElement(serializer:value:)")));
- (NSString *)encodeToStringSerializer:(id<Ssi_agentKotlinx_serialization_coreSerializationStrategy>)serializer value:(id _Nullable)value __attribute__((swift_name("encodeToString(serializer:value:)")));
- (Ssi_agentKotlinx_serialization_jsonJsonElement *)parseToJsonElementString:(NSString *)string __attribute__((swift_name("parseToJsonElement(string:)")));
@property (readonly) Ssi_agentKotlinx_serialization_coreSerializersModule *serializersModule __attribute__((swift_name("serializersModule")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("KotlinByteArray")))
@interface Ssi_agentKotlinByteArray : Ssi_agentBase
+ (instancetype)arrayWithSize:(int32_t)size __attribute__((swift_name("init(size:)")));
+ (instancetype)arrayWithSize:(int32_t)size init:(Ssi_agentByte *(^)(Ssi_agentInt *))init __attribute__((swift_name("init(size:init:)")));
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
- (int8_t)getIndex:(int32_t)index __attribute__((swift_name("get(index:)")));
- (Ssi_agentKotlinByteIterator *)iterator __attribute__((swift_name("iterator()")));
- (void)setIndex:(int32_t)index value:(int8_t)value __attribute__((swift_name("set(index:value:)")));
@property (readonly) int32_t size __attribute__((swift_name("size")));
@end;

__attribute__((swift_name("KotlinIterator")))
@protocol Ssi_agentKotlinIterator
@required
- (BOOL)hasNext __attribute__((swift_name("hasNext()")));
- (id _Nullable)next __attribute__((swift_name("next()")));
@end;

__attribute__((swift_name("Kotlinx_coroutines_coreSelectClause2")))
@protocol Ssi_agentKotlinx_coroutines_coreSelectClause2
@required
- (void)registerSelectClause2Select:(id<Ssi_agentKotlinx_coroutines_coreSelectInstance>)select param:(id _Nullable)param block:(id<Ssi_agentKotlinSuspendFunction1>)block __attribute__((swift_name("registerSelectClause2(select:param:block:)")));
@end;

__attribute__((swift_name("Kotlinx_coroutines_coreCancellationException")))
@interface Ssi_agentKotlinx_coroutines_coreCancellationException : Ssi_agentKotlinIllegalStateException
- (instancetype)initWithMessage:(NSString * _Nullable)message __attribute__((swift_name("init(message:)"))) __attribute__((objc_designated_initializer));
- (instancetype)initWithMessage:(NSString * _Nullable)message cause:(Ssi_agentKotlinThrowable * _Nullable)cause __attribute__((swift_name("init(message:cause:)"))) __attribute__((objc_designated_initializer));
- (instancetype)init __attribute__((swift_name("init()"))) __attribute__((objc_designated_initializer)) __attribute__((unavailable));
+ (instancetype)new __attribute__((unavailable));
- (instancetype)initWithCause:(Ssi_agentKotlinThrowable * _Nullable)cause __attribute__((swift_name("init(cause:)"))) __attribute__((objc_designated_initializer)) __attribute__((unavailable));
@end;

__attribute__((swift_name("Kotlinx_coroutines_coreChannelIterator")))
@protocol Ssi_agentKotlinx_coroutines_coreChannelIterator
@required

/**
 @note This method converts instances of CancellationException to errors.
 Other uncaught Kotlin exceptions are fatal.
*/
- (void)hasNextWithCompletionHandler:(void (^)(Ssi_agentBoolean * _Nullable, NSError * _Nullable))completionHandler __attribute__((swift_name("hasNext(completionHandler:)")));
- (id _Nullable)next __attribute__((swift_name("next()")));
@end;

__attribute__((swift_name("Kotlinx_coroutines_coreSelectClause1")))
@protocol Ssi_agentKotlinx_coroutines_coreSelectClause1
@required
- (void)registerSelectClause1Select:(id<Ssi_agentKotlinx_coroutines_coreSelectInstance>)select block:(id<Ssi_agentKotlinSuspendFunction1>)block __attribute__((swift_name("registerSelectClause1(select:block:)")));
@end;

__attribute__((swift_name("Kotlinx_serialization_coreCompositeEncoder")))
@protocol Ssi_agentKotlinx_serialization_coreCompositeEncoder
@required
- (void)encodeBooleanElementDescriptor:(id<Ssi_agentKotlinx_serialization_coreSerialDescriptor>)descriptor index:(int32_t)index value:(BOOL)value __attribute__((swift_name("encodeBooleanElement(descriptor:index:value:)")));
- (void)encodeByteElementDescriptor:(id<Ssi_agentKotlinx_serialization_coreSerialDescriptor>)descriptor index:(int32_t)index value:(int8_t)value __attribute__((swift_name("encodeByteElement(descriptor:index:value:)")));
- (void)encodeCharElementDescriptor:(id<Ssi_agentKotlinx_serialization_coreSerialDescriptor>)descriptor index:(int32_t)index value:(unichar)value __attribute__((swift_name("encodeCharElement(descriptor:index:value:)")));
- (void)encodeDoubleElementDescriptor:(id<Ssi_agentKotlinx_serialization_coreSerialDescriptor>)descriptor index:(int32_t)index value:(double)value __attribute__((swift_name("encodeDoubleElement(descriptor:index:value:)")));
- (void)encodeFloatElementDescriptor:(id<Ssi_agentKotlinx_serialization_coreSerialDescriptor>)descriptor index:(int32_t)index value:(float)value __attribute__((swift_name("encodeFloatElement(descriptor:index:value:)")));
- (void)encodeIntElementDescriptor:(id<Ssi_agentKotlinx_serialization_coreSerialDescriptor>)descriptor index:(int32_t)index value:(int32_t)value __attribute__((swift_name("encodeIntElement(descriptor:index:value:)")));
- (void)encodeLongElementDescriptor:(id<Ssi_agentKotlinx_serialization_coreSerialDescriptor>)descriptor index:(int32_t)index value:(int64_t)value __attribute__((swift_name("encodeLongElement(descriptor:index:value:)")));
- (void)encodeNullableSerializableElementDescriptor:(id<Ssi_agentKotlinx_serialization_coreSerialDescriptor>)descriptor index:(int32_t)index serializer:(id<Ssi_agentKotlinx_serialization_coreSerializationStrategy>)serializer value:(id _Nullable)value __attribute__((swift_name("encodeNullableSerializableElement(descriptor:index:serializer:value:)")));
- (void)encodeSerializableElementDescriptor:(id<Ssi_agentKotlinx_serialization_coreSerialDescriptor>)descriptor index:(int32_t)index serializer:(id<Ssi_agentKotlinx_serialization_coreSerializationStrategy>)serializer value:(id _Nullable)value __attribute__((swift_name("encodeSerializableElement(descriptor:index:serializer:value:)")));
- (void)encodeShortElementDescriptor:(id<Ssi_agentKotlinx_serialization_coreSerialDescriptor>)descriptor index:(int32_t)index value:(int16_t)value __attribute__((swift_name("encodeShortElement(descriptor:index:value:)")));
- (void)encodeStringElementDescriptor:(id<Ssi_agentKotlinx_serialization_coreSerialDescriptor>)descriptor index:(int32_t)index value:(NSString *)value __attribute__((swift_name("encodeStringElement(descriptor:index:value:)")));
- (void)endStructureDescriptor:(id<Ssi_agentKotlinx_serialization_coreSerialDescriptor>)descriptor __attribute__((swift_name("endStructure(descriptor:)")));
- (BOOL)shouldEncodeElementDefaultDescriptor:(id<Ssi_agentKotlinx_serialization_coreSerialDescriptor>)descriptor index:(int32_t)index __attribute__((swift_name("shouldEncodeElementDefault(descriptor:index:)")));
@property (readonly) Ssi_agentKotlinx_serialization_coreSerializersModule *serializersModule __attribute__((swift_name("serializersModule")));
@end;

__attribute__((swift_name("Kotlinx_serialization_coreSerializersModule")))
@interface Ssi_agentKotlinx_serialization_coreSerializersModule : Ssi_agentBase
- (void)dumpToCollector:(id<Ssi_agentKotlinx_serialization_coreSerializersModuleCollector>)collector __attribute__((swift_name("dumpTo(collector:)")));
- (id<Ssi_agentKotlinx_serialization_coreKSerializer> _Nullable)getContextualKclass:(id<Ssi_agentKotlinKClass>)kclass __attribute__((swift_name("getContextual(kclass:)")));
- (id<Ssi_agentKotlinx_serialization_coreSerializationStrategy> _Nullable)getPolymorphicBaseClass:(id<Ssi_agentKotlinKClass>)baseClass value:(id)value __attribute__((swift_name("getPolymorphic(baseClass:value:)")));
- (id<Ssi_agentKotlinx_serialization_coreDeserializationStrategy> _Nullable)getPolymorphicBaseClass:(id<Ssi_agentKotlinKClass>)baseClass serializedClassName:(NSString * _Nullable)serializedClassName __attribute__((swift_name("getPolymorphic(baseClass:serializedClassName:)")));
@end;

__attribute__((swift_name("KotlinAnnotation")))
@protocol Ssi_agentKotlinAnnotation
@required
@end;

__attribute__((swift_name("Kotlinx_serialization_coreSerialKind")))
@interface Ssi_agentKotlinx_serialization_coreSerialKind : Ssi_agentBase
- (NSUInteger)hash __attribute__((swift_name("hash()")));
- (NSString *)description __attribute__((swift_name("description()")));
@end;

__attribute__((swift_name("Kotlinx_serialization_coreCompositeDecoder")))
@protocol Ssi_agentKotlinx_serialization_coreCompositeDecoder
@required
- (BOOL)decodeBooleanElementDescriptor:(id<Ssi_agentKotlinx_serialization_coreSerialDescriptor>)descriptor index:(int32_t)index __attribute__((swift_name("decodeBooleanElement(descriptor:index:)")));
- (int8_t)decodeByteElementDescriptor:(id<Ssi_agentKotlinx_serialization_coreSerialDescriptor>)descriptor index:(int32_t)index __attribute__((swift_name("decodeByteElement(descriptor:index:)")));
- (unichar)decodeCharElementDescriptor:(id<Ssi_agentKotlinx_serialization_coreSerialDescriptor>)descriptor index:(int32_t)index __attribute__((swift_name("decodeCharElement(descriptor:index:)")));
- (int32_t)decodeCollectionSizeDescriptor:(id<Ssi_agentKotlinx_serialization_coreSerialDescriptor>)descriptor __attribute__((swift_name("decodeCollectionSize(descriptor:)")));
- (double)decodeDoubleElementDescriptor:(id<Ssi_agentKotlinx_serialization_coreSerialDescriptor>)descriptor index:(int32_t)index __attribute__((swift_name("decodeDoubleElement(descriptor:index:)")));
- (int32_t)decodeElementIndexDescriptor:(id<Ssi_agentKotlinx_serialization_coreSerialDescriptor>)descriptor __attribute__((swift_name("decodeElementIndex(descriptor:)")));
- (float)decodeFloatElementDescriptor:(id<Ssi_agentKotlinx_serialization_coreSerialDescriptor>)descriptor index:(int32_t)index __attribute__((swift_name("decodeFloatElement(descriptor:index:)")));
- (int32_t)decodeIntElementDescriptor:(id<Ssi_agentKotlinx_serialization_coreSerialDescriptor>)descriptor index:(int32_t)index __attribute__((swift_name("decodeIntElement(descriptor:index:)")));
- (int64_t)decodeLongElementDescriptor:(id<Ssi_agentKotlinx_serialization_coreSerialDescriptor>)descriptor index:(int32_t)index __attribute__((swift_name("decodeLongElement(descriptor:index:)")));
- (id _Nullable)decodeNullableSerializableElementDescriptor:(id<Ssi_agentKotlinx_serialization_coreSerialDescriptor>)descriptor index:(int32_t)index deserializer:(id<Ssi_agentKotlinx_serialization_coreDeserializationStrategy>)deserializer previousValue:(id _Nullable)previousValue __attribute__((swift_name("decodeNullableSerializableElement(descriptor:index:deserializer:previousValue:)")));
- (BOOL)decodeSequentially __attribute__((swift_name("decodeSequentially()")));
- (id _Nullable)decodeSerializableElementDescriptor:(id<Ssi_agentKotlinx_serialization_coreSerialDescriptor>)descriptor index:(int32_t)index deserializer:(id<Ssi_agentKotlinx_serialization_coreDeserializationStrategy>)deserializer previousValue:(id _Nullable)previousValue __attribute__((swift_name("decodeSerializableElement(descriptor:index:deserializer:previousValue:)")));
- (int16_t)decodeShortElementDescriptor:(id<Ssi_agentKotlinx_serialization_coreSerialDescriptor>)descriptor index:(int32_t)index __attribute__((swift_name("decodeShortElement(descriptor:index:)")));
- (NSString *)decodeStringElementDescriptor:(id<Ssi_agentKotlinx_serialization_coreSerialDescriptor>)descriptor index:(int32_t)index __attribute__((swift_name("decodeStringElement(descriptor:index:)")));
- (void)endStructureDescriptor:(id<Ssi_agentKotlinx_serialization_coreSerialDescriptor>)descriptor __attribute__((swift_name("endStructure(descriptor:)")));
@property (readonly) Ssi_agentKotlinx_serialization_coreSerializersModule *serializersModule __attribute__((swift_name("serializersModule")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("KotlinNothing")))
@interface Ssi_agentKotlinNothing : Ssi_agentBase
@end;

__attribute__((swift_name("KotlinCoroutineContextKey")))
@protocol Ssi_agentKotlinCoroutineContextKey
@required
@end;

__attribute__((swift_name("Kotlinx_coroutines_coreDisposableHandle")))
@protocol Ssi_agentKotlinx_coroutines_coreDisposableHandle
@required
- (void)dispose __attribute__((swift_name("dispose()")));
@end;

__attribute__((swift_name("Kotlinx_coroutines_coreChildHandle")))
@protocol Ssi_agentKotlinx_coroutines_coreChildHandle <Ssi_agentKotlinx_coroutines_coreDisposableHandle>
@required
- (BOOL)childCancelledCause:(Ssi_agentKotlinThrowable *)cause __attribute__((swift_name("childCancelled(cause:)")));
@end;

__attribute__((swift_name("Kotlinx_coroutines_coreChildJob")))
@protocol Ssi_agentKotlinx_coroutines_coreChildJob <Ssi_agentKotlinx_coroutines_coreJob>
@required
- (void)parentCancelledParentJob:(id<Ssi_agentKotlinx_coroutines_coreParentJob>)parentJob __attribute__((swift_name("parentCancelled(parentJob:)")));
@end;

__attribute__((swift_name("KotlinSequence")))
@protocol Ssi_agentKotlinSequence
@required
- (id<Ssi_agentKotlinIterator>)iterator __attribute__((swift_name("iterator()")));
@end;

__attribute__((swift_name("Kotlinx_coroutines_coreSelectClause0")))
@protocol Ssi_agentKotlinx_coroutines_coreSelectClause0
@required
- (void)registerSelectClause0Select:(id<Ssi_agentKotlinx_coroutines_coreSelectInstance>)select block:(id<Ssi_agentKotlinSuspendFunction0>)block __attribute__((swift_name("registerSelectClause0(select:block:)")));
@end;

__attribute__((swift_name("Kotlinx_serialization_jsonJsonElement")))
@interface Ssi_agentKotlinx_serialization_jsonJsonElement : Ssi_agentBase
@end;

__attribute__((swift_name("KotlinByteIterator")))
@interface Ssi_agentKotlinByteIterator : Ssi_agentBase <Ssi_agentKotlinIterator>
- (instancetype)init __attribute__((swift_name("init()"))) __attribute__((objc_designated_initializer));
+ (instancetype)new __attribute__((availability(swift, unavailable, message="use object initializers instead")));
- (Ssi_agentByte *)next __attribute__((swift_name("next()")));
- (int8_t)nextByte __attribute__((swift_name("nextByte()")));
@end;

__attribute__((swift_name("Kotlinx_coroutines_coreSelectInstance")))
@protocol Ssi_agentKotlinx_coroutines_coreSelectInstance
@required
- (void)disposeOnSelectHandle:(id<Ssi_agentKotlinx_coroutines_coreDisposableHandle>)handle __attribute__((swift_name("disposeOnSelect(handle:)")));
- (id _Nullable)performAtomicTrySelectDesc:(Ssi_agentKotlinx_coroutines_coreAtomicDesc *)desc __attribute__((swift_name("performAtomicTrySelect(desc:)")));
- (void)resumeSelectWithExceptionException:(Ssi_agentKotlinThrowable *)exception __attribute__((swift_name("resumeSelectWithException(exception:)")));
- (BOOL)trySelect __attribute__((swift_name("trySelect()")));
- (id _Nullable)trySelectOtherOtherOp:(Ssi_agentKotlinx_coroutines_coreLockFreeLinkedListNodePrepareOp * _Nullable)otherOp __attribute__((swift_name("trySelectOther(otherOp:)")));
@property (readonly) id<Ssi_agentKotlinContinuation> completion __attribute__((swift_name("completion")));
@property (readonly) BOOL isSelected __attribute__((swift_name("isSelected")));
@end;

__attribute__((swift_name("KotlinFunction")))
@protocol Ssi_agentKotlinFunction
@required
@end;

__attribute__((swift_name("KotlinSuspendFunction1")))
@protocol Ssi_agentKotlinSuspendFunction1 <Ssi_agentKotlinFunction>
@required

/**
 @note This method converts instances of CancellationException to errors.
 Other uncaught Kotlin exceptions are fatal.
*/
- (void)invokeP1:(id _Nullable)p1 completionHandler:(void (^)(id _Nullable, NSError * _Nullable))completionHandler __attribute__((swift_name("invoke(p1:completionHandler:)")));
@end;

__attribute__((swift_name("Kotlinx_serialization_coreSerializersModuleCollector")))
@protocol Ssi_agentKotlinx_serialization_coreSerializersModuleCollector
@required
- (void)contextualKClass:(id<Ssi_agentKotlinKClass>)kClass serializer:(id<Ssi_agentKotlinx_serialization_coreKSerializer>)serializer __attribute__((swift_name("contextual(kClass:serializer:)")));
- (void)polymorphicBaseClass:(id<Ssi_agentKotlinKClass>)baseClass actualClass:(id<Ssi_agentKotlinKClass>)actualClass actualSerializer:(id<Ssi_agentKotlinx_serialization_coreKSerializer>)actualSerializer __attribute__((swift_name("polymorphic(baseClass:actualClass:actualSerializer:)")));
- (void)polymorphicDefaultBaseClass:(id<Ssi_agentKotlinKClass>)baseClass defaultSerializerProvider:(id<Ssi_agentKotlinx_serialization_coreDeserializationStrategy> _Nullable (^)(NSString * _Nullable))defaultSerializerProvider __attribute__((swift_name("polymorphicDefault(baseClass:defaultSerializerProvider:)")));
@end;

__attribute__((swift_name("KotlinKDeclarationContainer")))
@protocol Ssi_agentKotlinKDeclarationContainer
@required
@end;

__attribute__((swift_name("KotlinKAnnotatedElement")))
@protocol Ssi_agentKotlinKAnnotatedElement
@required
@end;

__attribute__((swift_name("KotlinKClassifier")))
@protocol Ssi_agentKotlinKClassifier
@required
@end;

__attribute__((swift_name("KotlinKClass")))
@protocol Ssi_agentKotlinKClass <Ssi_agentKotlinKDeclarationContainer, Ssi_agentKotlinKAnnotatedElement, Ssi_agentKotlinKClassifier>
@required
- (BOOL)isInstanceValue:(id _Nullable)value __attribute__((swift_name("isInstance(value:)")));
@property (readonly) NSString * _Nullable qualifiedName __attribute__((swift_name("qualifiedName")));
@property (readonly) NSString * _Nullable simpleName __attribute__((swift_name("simpleName")));
@end;

__attribute__((swift_name("Kotlinx_coroutines_coreParentJob")))
@protocol Ssi_agentKotlinx_coroutines_coreParentJob <Ssi_agentKotlinx_coroutines_coreJob>
@required
- (Ssi_agentKotlinx_coroutines_coreCancellationException *)getChildJobCancellationCause __attribute__((swift_name("getChildJobCancellationCause()")));
@end;

__attribute__((swift_name("KotlinSuspendFunction0")))
@protocol Ssi_agentKotlinSuspendFunction0 <Ssi_agentKotlinFunction>
@required

/**
 @note This method converts instances of CancellationException to errors.
 Other uncaught Kotlin exceptions are fatal.
*/
- (void)invokeWithCompletionHandler:(void (^)(id _Nullable, NSError * _Nullable))completionHandler __attribute__((swift_name("invoke(completionHandler:)")));
@end;

__attribute__((swift_name("Kotlinx_coroutines_coreAtomicDesc")))
@interface Ssi_agentKotlinx_coroutines_coreAtomicDesc : Ssi_agentBase
- (instancetype)init __attribute__((swift_name("init()"))) __attribute__((objc_designated_initializer));
+ (instancetype)new __attribute__((availability(swift, unavailable, message="use object initializers instead")));
- (void)completeOp:(Ssi_agentKotlinx_coroutines_coreAtomicOp<id> *)op failure:(id _Nullable)failure __attribute__((swift_name("complete(op:failure:)")));
- (id _Nullable)prepareOp:(Ssi_agentKotlinx_coroutines_coreAtomicOp<id> *)op __attribute__((swift_name("prepare(op:)")));
@property Ssi_agentKotlinx_coroutines_coreAtomicOp<id> *atomicOp __attribute__((swift_name("atomicOp")));
@end;

__attribute__((swift_name("Kotlinx_coroutines_coreOpDescriptor")))
@interface Ssi_agentKotlinx_coroutines_coreOpDescriptor : Ssi_agentBase
- (instancetype)init __attribute__((swift_name("init()"))) __attribute__((objc_designated_initializer));
+ (instancetype)new __attribute__((availability(swift, unavailable, message="use object initializers instead")));
- (BOOL)isEarlierThanThat:(Ssi_agentKotlinx_coroutines_coreOpDescriptor *)that __attribute__((swift_name("isEarlierThan(that:)")));
- (id _Nullable)performAffected:(id _Nullable)affected __attribute__((swift_name("perform(affected:)")));
- (NSString *)description __attribute__((swift_name("description()")));
@property (readonly) Ssi_agentKotlinx_coroutines_coreAtomicOp<id> * _Nullable atomicOp __attribute__((swift_name("atomicOp")));
@end;

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("Kotlinx_coroutines_coreLockFreeLinkedListNode.PrepareOp")))
@interface Ssi_agentKotlinx_coroutines_coreLockFreeLinkedListNodePrepareOp : Ssi_agentKotlinx_coroutines_coreOpDescriptor
- (instancetype)initWithAffected:(Ssi_agentKotlinx_coroutines_coreLockFreeLinkedListNode *)affected next:(Ssi_agentKotlinx_coroutines_coreLockFreeLinkedListNode *)next desc:(Ssi_agentKotlinx_coroutines_coreLockFreeLinkedListNodeAbstractAtomicDesc *)desc __attribute__((swift_name("init(affected:next:desc:)"))) __attribute__((objc_designated_initializer));
- (instancetype)init __attribute__((swift_name("init()"))) __attribute__((objc_designated_initializer)) __attribute__((unavailable));
+ (instancetype)new __attribute__((unavailable));
- (void)finishPrepare __attribute__((swift_name("finishPrepare()")));
- (id _Nullable)performAffected:(id _Nullable)affected __attribute__((swift_name("perform(affected:)")));
- (NSString *)description __attribute__((swift_name("description()")));
@property (readonly) Ssi_agentKotlinx_coroutines_coreLockFreeLinkedListNode *affected __attribute__((swift_name("affected")));
@property (readonly) Ssi_agentKotlinx_coroutines_coreAtomicOp<id> *atomicOp __attribute__((swift_name("atomicOp")));
@property (readonly) Ssi_agentKotlinx_coroutines_coreLockFreeLinkedListNodeAbstractAtomicDesc *desc __attribute__((swift_name("desc")));
@property (readonly) Ssi_agentKotlinx_coroutines_coreLockFreeLinkedListNode *next __attribute__((swift_name("next")));
@end;

__attribute__((swift_name("KotlinContinuation")))
@protocol Ssi_agentKotlinContinuation
@required
- (void)resumeWithResult:(id _Nullable)result __attribute__((swift_name("resumeWith(result:)")));
@property (readonly) id<Ssi_agentKotlinCoroutineContext> context __attribute__((swift_name("context")));
@end;

__attribute__((swift_name("Kotlinx_coroutines_coreAtomicOp")))
@interface Ssi_agentKotlinx_coroutines_coreAtomicOp<__contravariant T> : Ssi_agentKotlinx_coroutines_coreOpDescriptor
- (instancetype)init __attribute__((swift_name("init()"))) __attribute__((objc_designated_initializer));
+ (instancetype)new __attribute__((availability(swift, unavailable, message="use object initializers instead")));
- (void)completeAffected:(T _Nullable)affected failure:(id _Nullable)failure __attribute__((swift_name("complete(affected:failure:)")));
- (id _Nullable)decideDecision:(id _Nullable)decision __attribute__((swift_name("decide(decision:)")));
- (id _Nullable)performAffected:(id _Nullable)affected __attribute__((swift_name("perform(affected:)")));
- (id _Nullable)prepareAffected:(T _Nullable)affected __attribute__((swift_name("prepare(affected:)")));
@property (readonly) Ssi_agentKotlinx_coroutines_coreAtomicOp<id> *atomicOp __attribute__((swift_name("atomicOp")));
@property (readonly) id _Nullable consensus __attribute__((swift_name("consensus")));
@property (readonly) BOOL isDecided __attribute__((swift_name("isDecided")));
@property (readonly) int64_t opSequence __attribute__((swift_name("opSequence")));
@end;

__attribute__((swift_name("Kotlinx_coroutines_coreLockFreeLinkedListNode")))
@interface Ssi_agentKotlinx_coroutines_coreLockFreeLinkedListNode : Ssi_agentBase
- (instancetype)init __attribute__((swift_name("init()"))) __attribute__((objc_designated_initializer));
+ (instancetype)new __attribute__((availability(swift, unavailable, message="use object initializers instead")));
- (void)addLastNode:(Ssi_agentKotlinx_coroutines_coreLockFreeLinkedListNode *)node __attribute__((swift_name("addLast(node:)")));
- (BOOL)addLastIfNode:(Ssi_agentKotlinx_coroutines_coreLockFreeLinkedListNode *)node condition:(Ssi_agentBoolean *(^)(void))condition __attribute__((swift_name("addLastIf(node:condition:)")));
- (BOOL)addLastIfPrevNode:(Ssi_agentKotlinx_coroutines_coreLockFreeLinkedListNode *)node predicate:(Ssi_agentBoolean *(^)(Ssi_agentKotlinx_coroutines_coreLockFreeLinkedListNode *))predicate __attribute__((swift_name("addLastIfPrev(node:predicate:)")));
- (BOOL)addLastIfPrevAndIfNode:(Ssi_agentKotlinx_coroutines_coreLockFreeLinkedListNode *)node predicate:(Ssi_agentBoolean *(^)(Ssi_agentKotlinx_coroutines_coreLockFreeLinkedListNode *))predicate condition:(Ssi_agentBoolean *(^)(void))condition __attribute__((swift_name("addLastIfPrevAndIf(node:predicate:condition:)")));
- (BOOL)addOneIfEmptyNode:(Ssi_agentKotlinx_coroutines_coreLockFreeLinkedListNode *)node __attribute__((swift_name("addOneIfEmpty(node:)")));
- (Ssi_agentKotlinx_coroutines_coreLockFreeLinkedListNodeAddLastDesc<Ssi_agentKotlinx_coroutines_coreLockFreeLinkedListNode *> *)describeAddLastNode:(Ssi_agentKotlinx_coroutines_coreLockFreeLinkedListNode *)node __attribute__((swift_name("describeAddLast(node:)")));
- (Ssi_agentKotlinx_coroutines_coreLockFreeLinkedListNodeRemoveFirstDesc<Ssi_agentKotlinx_coroutines_coreLockFreeLinkedListNode *> *)describeRemoveFirst __attribute__((swift_name("describeRemoveFirst()")));
- (void)helpRemove __attribute__((swift_name("helpRemove()")));
- (Ssi_agentKotlinx_coroutines_coreLockFreeLinkedListNode * _Nullable)nextIfRemoved __attribute__((swift_name("nextIfRemoved()")));
- (BOOL)remove __attribute__((swift_name("remove()")));
- (id _Nullable)removeFirstIfIsInstanceOfOrPeekIfPredicate:(Ssi_agentBoolean *(^)(id _Nullable))predicate __attribute__((swift_name("removeFirstIfIsInstanceOfOrPeekIf(predicate:)")));
- (Ssi_agentKotlinx_coroutines_coreLockFreeLinkedListNode * _Nullable)removeFirstOrNull __attribute__((swift_name("removeFirstOrNull()")));
- (NSString *)description __attribute__((swift_name("description()")));
@property (readonly) BOOL isRemoved __attribute__((swift_name("isRemoved")));
@property (readonly, getter=next_) id _Nullable next __attribute__((swift_name("next")));
@property (readonly) Ssi_agentKotlinx_coroutines_coreLockFreeLinkedListNode *nextNode __attribute__((swift_name("nextNode")));
@property (readonly) Ssi_agentKotlinx_coroutines_coreLockFreeLinkedListNode *prevNode __attribute__((swift_name("prevNode")));
@end;

__attribute__((swift_name("Kotlinx_coroutines_coreLockFreeLinkedListNode.AbstractAtomicDesc")))
@interface Ssi_agentKotlinx_coroutines_coreLockFreeLinkedListNodeAbstractAtomicDesc : Ssi_agentKotlinx_coroutines_coreAtomicDesc
- (instancetype)init __attribute__((swift_name("init()"))) __attribute__((objc_designated_initializer));
+ (instancetype)new __attribute__((availability(swift, unavailable, message="use object initializers instead")));
- (void)completeOp:(Ssi_agentKotlinx_coroutines_coreAtomicOp<id> *)op failure:(id _Nullable)failure __attribute__((swift_name("complete(op:failure:)")));
- (id _Nullable)failureAffected:(Ssi_agentKotlinx_coroutines_coreLockFreeLinkedListNode * _Nullable)affected __attribute__((swift_name("failure(affected:)")));
- (void)finishOnSuccessAffected:(Ssi_agentKotlinx_coroutines_coreLockFreeLinkedListNode *)affected next:(Ssi_agentKotlinx_coroutines_coreLockFreeLinkedListNode *)next __attribute__((swift_name("finishOnSuccess(affected:next:)")));
- (void)finishPreparePrepareOp:(Ssi_agentKotlinx_coroutines_coreLockFreeLinkedListNodePrepareOp *)prepareOp __attribute__((swift_name("finishPrepare(prepareOp:)")));
- (id _Nullable)onPreparePrepareOp:(Ssi_agentKotlinx_coroutines_coreLockFreeLinkedListNodePrepareOp *)prepareOp __attribute__((swift_name("onPrepare(prepareOp:)")));
- (void)onRemovedAffected:(Ssi_agentKotlinx_coroutines_coreLockFreeLinkedListNode *)affected __attribute__((swift_name("onRemoved(affected:)")));
- (id _Nullable)prepareOp:(Ssi_agentKotlinx_coroutines_coreAtomicOp<id> *)op __attribute__((swift_name("prepare(op:)")));
- (BOOL)retryAffected:(Ssi_agentKotlinx_coroutines_coreLockFreeLinkedListNode *)affected next:(id)next __attribute__((swift_name("retry(affected:next:)")));
- (Ssi_agentKotlinx_coroutines_coreLockFreeLinkedListNode * _Nullable)takeAffectedNodeOp:(Ssi_agentKotlinx_coroutines_coreOpDescriptor *)op __attribute__((swift_name("takeAffectedNode(op:)")));
- (id)updatedNextAffected:(Ssi_agentKotlinx_coroutines_coreLockFreeLinkedListNode *)affected next:(Ssi_agentKotlinx_coroutines_coreLockFreeLinkedListNode *)next __attribute__((swift_name("updatedNext(affected:next:)")));
@property (readonly) Ssi_agentKotlinx_coroutines_coreLockFreeLinkedListNode * _Nullable affectedNode __attribute__((swift_name("affectedNode")));
@property (readonly) Ssi_agentKotlinx_coroutines_coreLockFreeLinkedListNode * _Nullable originalNext __attribute__((swift_name("originalNext")));
@end;

__attribute__((swift_name("Kotlinx_coroutines_coreLockFreeLinkedListNodeAddLastDesc")))
@interface Ssi_agentKotlinx_coroutines_coreLockFreeLinkedListNodeAddLastDesc<T> : Ssi_agentKotlinx_coroutines_coreLockFreeLinkedListNodeAbstractAtomicDesc
- (instancetype)initWithQueue:(Ssi_agentKotlinx_coroutines_coreLockFreeLinkedListNode *)queue node:(T)node __attribute__((swift_name("init(queue:node:)"))) __attribute__((objc_designated_initializer));
- (instancetype)init __attribute__((swift_name("init()"))) __attribute__((objc_designated_initializer)) __attribute__((unavailable));
+ (instancetype)new __attribute__((unavailable));
- (void)finishOnSuccessAffected:(Ssi_agentKotlinx_coroutines_coreLockFreeLinkedListNode *)affected next:(Ssi_agentKotlinx_coroutines_coreLockFreeLinkedListNode *)next __attribute__((swift_name("finishOnSuccess(affected:next:)")));
- (void)finishPreparePrepareOp:(Ssi_agentKotlinx_coroutines_coreLockFreeLinkedListNodePrepareOp *)prepareOp __attribute__((swift_name("finishPrepare(prepareOp:)")));
- (BOOL)retryAffected:(Ssi_agentKotlinx_coroutines_coreLockFreeLinkedListNode *)affected next:(id)next __attribute__((swift_name("retry(affected:next:)")));
- (Ssi_agentKotlinx_coroutines_coreLockFreeLinkedListNode * _Nullable)takeAffectedNodeOp:(Ssi_agentKotlinx_coroutines_coreOpDescriptor *)op __attribute__((swift_name("takeAffectedNode(op:)")));
- (id)updatedNextAffected:(Ssi_agentKotlinx_coroutines_coreLockFreeLinkedListNode *)affected next:(Ssi_agentKotlinx_coroutines_coreLockFreeLinkedListNode *)next __attribute__((swift_name("updatedNext(affected:next:)")));
@property (readonly) Ssi_agentKotlinx_coroutines_coreLockFreeLinkedListNode * _Nullable affectedNode __attribute__((swift_name("affectedNode")));
@property (readonly) T node __attribute__((swift_name("node")));
@property (readonly) Ssi_agentKotlinx_coroutines_coreLockFreeLinkedListNode * _Nullable originalNext __attribute__((swift_name("originalNext")));
@property (readonly) Ssi_agentKotlinx_coroutines_coreLockFreeLinkedListNode *queue __attribute__((swift_name("queue")));
@end;

__attribute__((swift_name("Kotlinx_coroutines_coreLockFreeLinkedListNodeRemoveFirstDesc")))
@interface Ssi_agentKotlinx_coroutines_coreLockFreeLinkedListNodeRemoveFirstDesc<T> : Ssi_agentKotlinx_coroutines_coreLockFreeLinkedListNodeAbstractAtomicDesc
- (instancetype)initWithQueue:(Ssi_agentKotlinx_coroutines_coreLockFreeLinkedListNode *)queue __attribute__((swift_name("init(queue:)"))) __attribute__((objc_designated_initializer));
- (instancetype)init __attribute__((swift_name("init()"))) __attribute__((objc_designated_initializer)) __attribute__((unavailable));
+ (instancetype)new __attribute__((unavailable));
- (id _Nullable)failureAffected:(Ssi_agentKotlinx_coroutines_coreLockFreeLinkedListNode * _Nullable)affected __attribute__((swift_name("failure(affected:)")));
- (void)finishOnSuccessAffected:(Ssi_agentKotlinx_coroutines_coreLockFreeLinkedListNode *)affected next:(Ssi_agentKotlinx_coroutines_coreLockFreeLinkedListNode *)next __attribute__((swift_name("finishOnSuccess(affected:next:)")));
- (void)finishPreparePrepareOp:(Ssi_agentKotlinx_coroutines_coreLockFreeLinkedListNodePrepareOp *)prepareOp __attribute__((swift_name("finishPrepare(prepareOp:)")));
- (BOOL)retryAffected:(Ssi_agentKotlinx_coroutines_coreLockFreeLinkedListNode *)affected next:(id)next __attribute__((swift_name("retry(affected:next:)")));
- (Ssi_agentKotlinx_coroutines_coreLockFreeLinkedListNode * _Nullable)takeAffectedNodeOp:(Ssi_agentKotlinx_coroutines_coreOpDescriptor *)op __attribute__((swift_name("takeAffectedNode(op:)")));
- (id)updatedNextAffected:(Ssi_agentKotlinx_coroutines_coreLockFreeLinkedListNode *)affected next:(Ssi_agentKotlinx_coroutines_coreLockFreeLinkedListNode *)next __attribute__((swift_name("updatedNext(affected:next:)")));
@property (readonly) Ssi_agentKotlinx_coroutines_coreLockFreeLinkedListNode * _Nullable affectedNode __attribute__((swift_name("affectedNode")));
@property (readonly) Ssi_agentKotlinx_coroutines_coreLockFreeLinkedListNode * _Nullable originalNext __attribute__((swift_name("originalNext")));
@property (readonly) Ssi_agentKotlinx_coroutines_coreLockFreeLinkedListNode *queue __attribute__((swift_name("queue")));
@property (readonly) T _Nullable result __attribute__((swift_name("result")));
@end;

#pragma clang diagnostic pop
NS_ASSUME_NONNULL_END
