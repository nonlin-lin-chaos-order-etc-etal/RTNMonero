import type {TurboModule} from 'react-native/Libraries/TurboModule/RCTExport';
import {TurboModuleRegistry} from 'react-native';

export interface Spec extends TurboModule {
  answerTheUltimateQuestion(input: string): Promise<number>;
}

export default TurboModuleRegistry.get<Spec>(
  'NativeMonero',
) as Spec | null;
