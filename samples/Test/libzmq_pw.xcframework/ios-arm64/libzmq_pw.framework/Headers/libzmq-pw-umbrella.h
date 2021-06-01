#ifdef __OBJC__
#import <UIKit/UIKit.h>
#else
#ifndef FOUNDATION_EXPORT
#if defined(__cplusplus)
#define FOUNDATION_EXPORT extern "C"
#else
#define FOUNDATION_EXPORT extern
#endif
#endif
#endif

#import "zmq.h"
#import "zmq_utils.h"

FOUNDATION_EXPORT double libzmq_pwVersionNumber;
FOUNDATION_EXPORT const unsigned char libzmq_pwVersionString[];

