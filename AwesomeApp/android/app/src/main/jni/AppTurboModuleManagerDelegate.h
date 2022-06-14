#include <memory>
#include <string>

#include <ReactCommon/TurboModuleManagerDelegate.h>
#include <fbjni/fbjni.h>

namespace facebook {
namespace react {

class AppTurboModuleManagerDelegate : public jni::HybridClass<AppTurboModuleManagerDelegate, TurboModuleManagerDelegate> {
public:
// Adapt it to the package you used for your Java class.
static constexpr auto kJavaDescriptor =
    "Lcom/awesomeapp/AppTurboModuleManagerDelegate;";

static jni::local_ref<jhybriddata> initHybrid(jni::alias_ref<jhybridobject>);

static void registerNatives();

std::shared_ptr<TurboModule> getTurboModule(const std::string name, const std::shared_ptr<CallInvoker> jsInvoker) override;
std::shared_ptr<TurboModule> getTurboModule(const std::string name, const JavaTurboModule::InitParams &params) override;

private:
friend HybridBase;
using HybridBase::HybridBase;

};

} // namespace react
} // namespace facebook
