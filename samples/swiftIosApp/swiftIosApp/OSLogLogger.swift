//
//  OSLogLogger.swift
//  swiftIosApp
//
//  Created by Krzysztof on 03/08/2021.
//

import Foundation
import ssi_agent
import os.log

class OSLogLogger: ssi_agent.KermitLogger {
    override func isLoggable(severity: KermitSeverity) -> Bool {
        let path: String = Bundle.main.path(forResource: "LoggerLevel", ofType: "plist")!
        let severityConf: NSDictionary = NSDictionary(contentsOfFile: path)!
        let severityLevel = severityConf.object(forKey: "level") as! String
        
        if(severityLevel == "verbose"){
            return severity == KermitSeverity.verbose
        }
        if(severityLevel == "debug"){
            return severity == KermitSeverity.debug
        }
        if(severityLevel == "info"){
            return severity == KermitSeverity.info
        }
        if(severityLevel == "warn"){
            return severity == KermitSeverity.warn
        }
        if(severityLevel == "error"){
            return severity == KermitSeverity.error
        }
        if(severityLevel == "assert"){
            return severity == KermitSeverity.assert
        }
        return severity == KermitSeverity.verbose
    }
   
    override func log(severity: KermitSeverity, message: String, tag: String, throwable: KotlinThrowable?) {
        if(isLoggable(severity:severity))
        {
            os_log("%@", log: OSLog(subsystem: tag , category: tag ), type: getSeverity(severity: severity), ("Current thread \(Thread.current): \(message)"))
            if let realThrowable = throwable {
                os_log("%@", log: OSLog(subsystem: tag , category: tag ), type: getSeverity(severity: severity), realThrowable.message ?? realThrowable.description)
            }
        }
    }
   
    private func getSeverity(severity: KermitSeverity) -> OSLogType {
        switch severity {
            case KermitSeverity.verbose: return OSLogType.info
            case KermitSeverity.debug: return OSLogType.debug
            case KermitSeverity.info: return OSLogType.info
            case KermitSeverity.warn: return OSLogType.debug
            case KermitSeverity.error: return OSLogType.error
            case KermitSeverity.assert: return OSLogType.fault
            default: return OSLogType.default
        }
    }
}
