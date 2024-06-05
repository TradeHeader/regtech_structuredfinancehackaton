package cdm.event.common.meta;

import cdm.event.common.CollateralPosition;
import cdm.event.common.validation.CollateralPositionTypeFormatValidator;
import cdm.event.common.validation.CollateralPositionValidator;
import cdm.event.common.validation.exists.CollateralPositionOnlyExistsValidator;
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
@RosettaMeta(model=CollateralPosition.class)
public class CollateralPositionMeta implements RosettaMetaData<CollateralPosition> {

	@Override
	public List<Validator<? super CollateralPosition>> dataRules(ValidatorFactory factory) {
		return Arrays.asList(
			factory.create(cdm.event.common.validation.datarule.CollateralPositionCollateralPositionStatusSettledOrInTransitOnly.class)
		);
	}
	
	@Override
	public List<Function<? super CollateralPosition, QualifyResult>> getQualifyFunctions(QualifyFunctionFactory factory) {
		return Collections.emptyList();
	}

	@Override
	public Validator<? super CollateralPosition> validator() {
		return new CollateralPositionValidator();
	}

	@Override
	public Validator<? super CollateralPosition> typeFormatValidator() {
		return new CollateralPositionTypeFormatValidator();
	}
	
	@Override
	public ValidatorWithArg<? super CollateralPosition, Set<String>> onlyExistsValidator() {
		return new CollateralPositionOnlyExistsValidator();
	}
}
