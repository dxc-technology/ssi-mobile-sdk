Pod::Spec.new do |s|
    s.name         = "ssi_agent"
    s.version      = "0.0.1"
    s.summary      = "Kotlin Multiplatform"
    s.description  = "Self Sovereign Identity Kotlin Multiplatform Agent.A new Mobile SDK to use with Self Sovereign Identity (SSI) applications is available as an open-source project from Luxoft DXC. The SSI Mobile SDK provides libraries and tools to accelerate integration of digital wallet capabilities into existing mobile apps."
    s.homepage     = "https://kotlinlang.org/docs/multiplatform.html"
    s.license      = "All the original code is licensed under the Apache 2.0 License."
    s.author       = { "Krzysztof Kamyczek" => "krzysztof.kamyczek@dxc.com" }
    s.source       = { :http => "https://github.com/Lumedic/ssi-mobile-sdk/releases/download/0.0.1/dxc-ssi-agent-ios-0.0.1.zip"}
    s.vendored_frameworks = "ssi_agent.xcframework"
    s.platform = :ios
    s.swift_version = "4.2"
    s.ios.deployment_target  = '10.2'
end
