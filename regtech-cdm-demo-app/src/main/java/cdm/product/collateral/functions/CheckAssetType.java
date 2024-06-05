package cdm.product.collateral.functions;

import cdm.base.staticdata.asset.common.AssetType;
import cdm.base.staticdata.asset.common.AssetTypeEnum;
import cdm.base.staticdata.asset.common.DebtClassEnum;
import cdm.base.staticdata.asset.common.DebtType;
import cdm.base.staticdata.asset.common.SecurityTypeEnum;
import cdm.product.collateral.EligibilityQuery;
import com.google.inject.ImplementedBy;
import com.rosetta.model.lib.expression.ComparisonResult;
import com.rosetta.model.lib.functions.RosettaFunction;
import com.rosetta.model.lib.mapper.MapperC;
import com.rosetta.model.lib.mapper.MapperS;
import java.util.Collections;
import java.util.List;

import static com.rosetta.model.lib.expression.ExpressionOperators.*;

@ImplementedBy(CheckAssetType.CheckAssetTypeDefault.class)
public abstract class CheckAssetType implements RosettaFunction {

	/**
	* @param collateralAssetTypes 
	* @param query 
	* @return isEqual 
	*/
	public Boolean evaluate(List<? extends AssetType> collateralAssetTypes, EligibilityQuery query) {
		Boolean isEqual = doEvaluate(collateralAssetTypes, query);
		
		return isEqual;
	}

	protected abstract Boolean doEvaluate(List<? extends AssetType> collateralAssetTypes, EligibilityQuery query);

	public static class CheckAssetTypeDefault extends CheckAssetType {
		@Override
		protected Boolean doEvaluate(List<? extends AssetType> collateralAssetTypes, EligibilityQuery query) {
			if (collateralAssetTypes == null) {
				collateralAssetTypes = Collections.emptyList();
			}
			Boolean isEqual = null;
			return assignOutput(isEqual, collateralAssetTypes, query);
		}
		
		protected Boolean assignOutput(Boolean isEqual, List<? extends AssetType> collateralAssetTypes, EligibilityQuery query) {
			final MapperC<AssetType> thenResult0 = MapperC.<AssetType>of(collateralAssetTypes);
			final MapperC<AssetType> thenResult1 = thenResult0
				.filterItemNullSafe(item -> contains(item.<AssetTypeEnum>map("getAssetType", assetType -> assetType.getAssetType()), MapperS.of(query).<AssetType>map("getCollateralAssetType", eligibilityQuery -> eligibilityQuery.getCollateralAssetType()).<AssetTypeEnum>map("getAssetType", assetType -> assetType.getAssetType())).get());
			final MapperC<AssetType> thenResult2 = thenResult1
				.filterItemNullSafe(item -> notExists(item.<DebtType>map("getDebtType", assetType -> assetType.getDebtType())).or(contains(item.<DebtType>map("getDebtType", assetType -> assetType.getDebtType()).<DebtClassEnum>map("getDebtClass", debtType -> debtType.getDebtClass()), MapperS.of(query).<AssetType>map("getCollateralAssetType", eligibilityQuery -> eligibilityQuery.getCollateralAssetType()).<DebtType>map("getDebtType", assetType -> assetType.getDebtType()).<DebtClassEnum>map("getDebtClass", debtType -> debtType.getDebtClass()))).get());
			final MapperC<AssetType> thenResult3 = thenResult2
				.filterItemNullSafe(item -> notExists(item.<SecurityTypeEnum>map("getSecurityType", assetType -> assetType.getSecurityType())).or(contains(item.<SecurityTypeEnum>map("getSecurityType", assetType -> assetType.getSecurityType()), MapperS.of(query).<AssetType>map("getCollateralAssetType", eligibilityQuery -> eligibilityQuery.getCollateralAssetType()).<SecurityTypeEnum>map("getSecurityType", assetType -> assetType.getSecurityType()))).get());
			isEqual = notExists(MapperC.<AssetType>of(collateralAssetTypes)).or(ComparisonResult.of(exists(thenResult3).asMapper())).get();
			
			return isEqual;
		}
	}
}
