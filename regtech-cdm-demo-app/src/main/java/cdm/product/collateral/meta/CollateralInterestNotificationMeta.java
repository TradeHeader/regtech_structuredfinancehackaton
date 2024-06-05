package cdm.product.collateral.meta;

import cdm.product.collateral.CollateralInterestNotification;
import cdm.product.collateral.validation.CollateralInterestNotificationTypeFormatValidator;
import cdm.product.collateral.validation.CollateralInterestNotificationValidator;
import cdm.product.collateral.validation.exists.CollateralInterestNotificationOnlyExistsValidator;
import com.rosetta.model.lib.annotations.RosettaMeta;
import com.rosetta.model.lib.meta.RosettaMetaData;
import com.rosetta.model.lib.qualify.QualifyFunctionFactory;
import com.rosetta.model.lib.qualify.QualifyResult;
import com.rosetta.model.lib.validation.Validator;
import com.rosetta.model.lib.validation.ValidatorFactory;
import com.rosetta.model.lib.validation.ValidatorWithArg;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.function.Function;


/**
 * @version ${project.version}
 */
@RosettaMeta(model=CollateralInterestNotification.class)
public class CollateralInterestNotificationMeta implements RosettaMetaData<CollateralInterestNotification> {

	@Override
	public List<Validator<? super CollateralInterestNotification>> dataRules(ValidatorFactory factory) {
		return Arrays.asList(
		);
	}
	
	@Override
	public List<Function<? super CollateralInterestNotification, QualifyResult>> getQualifyFunctions(QualifyFunctionFactory factory) {
		return Collections.emptyList();
	}

	@Override
	public Validator<? super CollateralInterestNotification> validator() {
		return new CollateralInterestNotificationValidator();
	}

	@Override
	public Validator<? super CollateralInterestNotification> typeFormatValidator() {
		return new CollateralInterestNotificationTypeFormatValidator();
	}
	
	@Override
	public ValidatorWithArg<? super CollateralInterestNotification, Set<String>> onlyExistsValidator() {
		return new CollateralInterestNotificationOnlyExistsValidator();
	}
}
