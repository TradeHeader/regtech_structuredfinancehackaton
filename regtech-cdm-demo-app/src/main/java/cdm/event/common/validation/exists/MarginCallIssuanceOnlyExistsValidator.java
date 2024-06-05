package cdm.event.common.validation.exists;

import cdm.base.staticdata.identifier.Identifier;
import cdm.base.staticdata.party.Party;
import cdm.base.staticdata.party.PartyRole;
import cdm.event.common.CollateralBalance;
import cdm.event.common.MarginCallExposure;
import cdm.event.common.MarginCallInstructionType;
import cdm.event.common.MarginCallIssuance;
import cdm.event.common.RegIMRoleEnum;
import cdm.event.common.RegMarginTypeEnum;
import cdm.event.common.metafields.ReferenceWithMetaCollateralPortfolio;
import cdm.legaldocumentation.common.AgreementName;
import cdm.observable.asset.Money;
import cdm.product.collateral.EligibleCollateralCriteria;
import com.google.common.collect.ImmutableMap;
import com.rosetta.model.lib.path.RosettaPath;
import com.rosetta.model.lib.validation.ExistenceChecker;
import com.rosetta.model.lib.validation.ValidationResult;
import com.rosetta.model.lib.validation.ValidationResult.ValidationType;
import com.rosetta.model.lib.validation.ValidatorWithArg;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import static com.rosetta.model.lib.validation.ValidationResult.failure;
import static com.rosetta.model.lib.validation.ValidationResult.success;

public class MarginCallIssuanceOnlyExistsValidator implements ValidatorWithArg<MarginCallIssuance, Set<String>> {

	/* Casting is required to ensure types are output to ensure recompilation in Rosetta */
	@Override
	public <T2 extends MarginCallIssuance> ValidationResult<MarginCallIssuance> validate(RosettaPath path, T2 o, Set<String> fields) {
		Map<String, Boolean> fieldExistenceMap = ImmutableMap.<String, Boolean>builder()
				.put("instructionType", ExistenceChecker.isSet((MarginCallInstructionType) o.getInstructionType()))
				.put("party", ExistenceChecker.isSet((List<? extends Party>) o.getParty()))
				.put("partyRole", ExistenceChecker.isSet((List<? extends PartyRole>) o.getPartyRole()))
				.put("clearingBroker", ExistenceChecker.isSet((Party) o.getClearingBroker()))
				.put("callIdentifier", ExistenceChecker.isSet((Identifier) o.getCallIdentifier()))
				.put("callAgreementType", ExistenceChecker.isSet((AgreementName) o.getCallAgreementType()))
				.put("agreementMinimumTransferAmount", ExistenceChecker.isSet((Money) o.getAgreementMinimumTransferAmount()))
				.put("agreementThreshold", ExistenceChecker.isSet((Money) o.getAgreementThreshold()))
				.put("agreementRounding", ExistenceChecker.isSet((Money) o.getAgreementRounding()))
				.put("regMarginType", ExistenceChecker.isSet((RegMarginTypeEnum) o.getRegMarginType()))
				.put("regIMRole", ExistenceChecker.isSet((RegIMRoleEnum) o.getRegIMRole()))
				.put("baseCurrencyExposure", ExistenceChecker.isSet((MarginCallExposure) o.getBaseCurrencyExposure()))
				.put("collateralPortfolio", ExistenceChecker.isSet((ReferenceWithMetaCollateralPortfolio) o.getCollateralPortfolio()))
				.put("independentAmountBalance", ExistenceChecker.isSet((CollateralBalance) o.getIndependentAmountBalance()))
				.put("callAmountInBaseCurrency", ExistenceChecker.isSet((Money) o.getCallAmountInBaseCurrency()))
				.put("recallNonCashCollateralDescription", ExistenceChecker.isSet((List<? extends EligibleCollateralCriteria>) o.getRecallNonCashCollateralDescription()))
				.build();
		
		// Find the fields that are set
		Set<String> setFields = fieldExistenceMap.entrySet().stream()
				.filter(Map.Entry::getValue)
				.map(Map.Entry::getKey)
				.collect(Collectors.toSet());
		
		if (setFields.equals(fields)) {
			return success("MarginCallIssuance", ValidationType.ONLY_EXISTS, "MarginCallIssuance", path, "");
		}
		return failure("MarginCallIssuance", ValidationType.ONLY_EXISTS, "MarginCallIssuance", path, "",
				String.format("[%s] should only be set.  Set fields: %s", fields, setFields));
	}
}
