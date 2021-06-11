Pod::Spec.new do |s|
    s.name         = "ssi_agent"
    s.version      = "1.0.0"
    s.summary      = "Kotlin Multiplatform"
    s.description  = ""
    s.homepage     = ""
    s.license      = ""
    s.author       = { "Krzysztof Kamyczek" => "krzysztof.kamyczek@dxc.com" }
    s.source       = { :http => "https://github.com/kkamyczek/ssi-mobile-sdk/releases/download/v1.0/ssi_agent.xcframework.zip"}
    s.vendored_frameworks = "ssi_agent.xcframework"
    s.platform = :ios
    s.swift_version = "4.2"
    s.ios.deployment_target  = '10.2'
end
