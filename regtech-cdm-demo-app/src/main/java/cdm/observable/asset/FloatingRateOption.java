package cdm.observable.asset;

import cdm.base.datetime.Period;
import cdm.base.staticdata.asset.common.IndexReferenceInformation;
import cdm.base.staticdata.asset.rates.FloatingRateIndexEnum;
import cdm.base.staticdata.asset.rates.InflationRateIndexEnum;
import cdm.base.staticdata.asset.rates.metafields.FieldWithMetaFloatingRateIndexEnum;
import cdm.base.staticdata.asset.rates.metafields.FieldWithMetaFloatingRateIndexEnum.FieldWithMetaFloatingRateIndexEnumBuilder;
import cdm.base.staticdata.asset.rates.metafields.FieldWithMetaInflationRateIndexEnum;
import cdm.base.staticdata.asset.rates.metafields.FieldWithMetaInflationRateIndexEnum.FieldWithMetaInflationRateIndexEnumBuilder;
import cdm.observable.asset.FloatingRateOption;
import cdm.observable.asset.FloatingRateOption.FloatingRateOptionBuilder;
import cdm.observable.asset.FloatingRateOption.FloatingRateOptionBuilderImpl;
import cdm.observable.asset.FloatingRateOption.FloatingRateOptionImpl;
import cdm.observable.asset.meta.FloatingRateOptionMeta;
import com.rosetta.model.lib.RosettaModelObject;
import com.rosetta.model.lib.RosettaModelObjectBuilder;
import com.rosetta.model.lib.annotations.RosettaAttribute;
import com.rosetta.model.lib.annotations.RosettaDataType;
import com.rosetta.model.lib.meta.RosettaMetaData;
import com.rosetta.model.lib.path.RosettaPath;
import com.rosetta.model.lib.process.BuilderMerger;
import com.rosetta.model.lib.process.BuilderProcessor;
import com.rosetta.model.lib.process.Processor;
import java.util.Objects;

import static java.util.Optional.ofNullable;

/**
 * Specification of a floating rate option as a floating rate index and tenor.
 * @version ${project.version}
 */
@RosettaDataType(value="FloatingRateOption", builder=FloatingRateOption.FloatingRateOptionBuilderImpl.class, version="${project.version}")
public interface FloatingRateOption extends RosettaModelObject {

	FloatingRateOptionMeta metaData = new FloatingRateOptionMeta();

	/*********************** Getter Methods  ***********************/
	/**
	 * The reference index that is used to specify the floating interest rate. The FpML standard maintains the list of such indices, which are positioned as enumeration values as part of the CDM.
	 */
	FieldWithMetaFloatingRateIndexEnum getFloatingRateIndex();
	/**
	 * The reference index that is used to specify the inflation interest rate. The FpML standard maintains the list of such indices, which are positioned as enumeration values as part of the CDM.
	 */
	FieldWithMetaInflationRateIndexEnum getInflationRateIndex();
	/**
	 * The ISDA Designated Maturity, i.e. the floating rate tenor.
	 */
	Period getIndexTenor();
	/**
	 * This Attribute contains all the terms relevant to defining an Index.
	 */
	IndexReferenceInformation getIndexReferenceInformation();

	/*********************** Build Methods  ***********************/
	FloatingRateOption build();
	
	FloatingRateOption.FloatingRateOptionBuilder toBuilder();
	
	static FloatingRateOption.FloatingRateOptionBuilder builder() {
		return new FloatingRateOption.FloatingRateOptionBuilderImpl();
	}

	/*********************** Utility Methods  ***********************/
	@Override
	default RosettaMetaData<? extends FloatingRateOption> metaData() {
		return metaData;
	}
	
	@Override
	default Class<? extends FloatingRateOption> getType() {
		return FloatingRateOption.class;
	}
	
	
	@Override
	default void process(RosettaPath path, Processor processor) {
		processRosetta(path.newSubPath("floatingRateIndex"), processor, FieldWithMetaFloatingRateIndexEnum.class, getFloatingRateIndex());
		processRosetta(path.newSubPath("inflationRateIndex"), processor, FieldWithMetaInflationRateIndexEnum.class, getInflationRateIndex());
		processRosetta(path.newSubPath("indexTenor"), processor, Period.class, getIndexTenor());
		processRosetta(path.newSubPath("indexReferenceInformation"), processor, IndexReferenceInformation.class, getIndexReferenceInformation());
	}
	

	/*********************** Builder Interface  ***********************/
	interface FloatingRateOptionBuilder extends FloatingRateOption, RosettaModelObjectBuilder {
		FieldWithMetaFloatingRateIndexEnum.FieldWithMetaFloatingRateIndexEnumBuilder getOrCreateFloatingRateIndex();
		FieldWithMetaFloatingRateIndexEnum.FieldWithMetaFloatingRateIndexEnumBuilder getFloatingRateIndex();
		FieldWithMetaInflationRateIndexEnum.FieldWithMetaInflationRateIndexEnumBuilder getOrCreateInflationRateIndex();
		FieldWithMetaInflationRateIndexEnum.FieldWithMetaInflationRateIndexEnumBuilder getInflationRateIndex();
		Period.PeriodBuilder getOrCreateIndexTenor();
		Period.PeriodBuilder getIndexTenor();
		IndexReferenceInformation.IndexReferenceInformationBuilder getOrCreateIndexReferenceInformation();
		IndexReferenceInformation.IndexReferenceInformationBuilder getIndexReferenceInformation();
		FloatingRateOption.FloatingRateOptionBuilder setFloatingRateIndex(FieldWithMetaFloatingRateIndexEnum floatingRateIndex0);
		FloatingRateOption.FloatingRateOptionBuilder setFloatingRateIndexValue(FloatingRateIndexEnum floatingRateIndex1);
		FloatingRateOption.FloatingRateOptionBuilder setInflationRateIndex(FieldWithMetaInflationRateIndexEnum inflationRateIndex0);
		FloatingRateOption.FloatingRateOptionBuilder setInflationRateIndexValue(InflationRateIndexEnum inflationRateIndex1);
		FloatingRateOption.FloatingRateOptionBuilder setIndexTenor(Period indexTenor);
		FloatingRateOption.FloatingRateOptionBuilder setIndexReferenceInformation(IndexReferenceInformation indexReferenceInformation);

		@Override
		default void process(RosettaPath path, BuilderProcessor processor) {
			processRosetta(path.newSubPath("floatingRateIndex"), processor, FieldWithMetaFloatingRateIndexEnum.FieldWithMetaFloatingRateIndexEnumBuilder.class, getFloatingRateIndex());
			processRosetta(path.newSubPath("inflationRateIndex"), processor, FieldWithMetaInflationRateIndexEnum.FieldWithMetaInflationRateIndexEnumBuilder.class, getInflationRateIndex());
			processRosetta(path.newSubPath("indexTenor"), processor, Period.PeriodBuilder.class, getIndexTenor());
			processRosetta(path.newSubPath("indexReferenceInformation"), processor, IndexReferenceInformation.IndexReferenceInformationBuilder.class, getIndexReferenceInformation());
		}
		

		FloatingRateOption.FloatingRateOptionBuilder prune();
	}

	/*********************** Immutable Implementation of FloatingRateOption  ***********************/
	class FloatingRateOptionImpl implements FloatingRateOption {
		private final FieldWithMetaFloatingRateIndexEnum floatingRateIndex;
		private final FieldWithMetaInflationRateIndexEnum inflationRateIndex;
		private final Period indexTenor;
		private final IndexReferenceInformation indexReferenceInformation;
		
		protected FloatingRateOptionImpl(FloatingRateOption.FloatingRateOptionBuilder builder) {
			this.floatingRateIndex = ofNullable(builder.getFloatingRateIndex()).map(f->f.build()).orElse(null);
			this.inflationRateIndex = ofNullable(builder.getInflationRateIndex()).map(f->f.build()).orElse(null);
			this.indexTenor = ofNullable(builder.getIndexTenor()).map(f->f.build()).orElse(null);
			this.indexReferenceInformation = ofNullable(builder.getIndexReferenceInformation()).map(f->f.build()).orElse(null);
		}
		
		@Override
		@RosettaAttribute("floatingRateIndex")
		public FieldWithMetaFloatingRateIndexEnum getFloatingRateIndex() {
			return floatingRateIndex;
		}
		
		@Override
		@RosettaAttribute("inflationRateIndex")
		public FieldWithMetaInflationRateIndexEnum getInflationRateIndex() {
			return inflationRateIndex;
		}
		
		@Override
		@RosettaAttribute("indexTenor")
		public Period getIndexTenor() {
			return indexTenor;
		}
		
		@Override
		@RosettaAttribute("indexReferenceInformation")
		public IndexReferenceInformation getIndexReferenceInformation() {
			return indexReferenceInformation;
		}
		
		@Override
		public FloatingRateOption build() {
			return this;
		}
		
		@Override
		public FloatingRateOption.FloatingRateOptionBuilder toBuilder() {
			FloatingRateOption.FloatingRateOptionBuilder builder = builder();
			setBuilderFields(builder);
			return builder;
		}
		
		protected void setBuilderFields(FloatingRateOption.FloatingRateOptionBuilder builder) {
			ofNullable(getFloatingRateIndex()).ifPresent(builder::setFloatingRateIndex);
			ofNullable(getInflationRateIndex()).ifPresent(builder::setInflationRateIndex);
			ofNullable(getIndexTenor()).ifPresent(builder::setIndexTenor);
			ofNullable(getIndexReferenceInformation()).ifPresent(builder::setIndexReferenceInformation);
		}

		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			FloatingRateOption _that = getType().cast(o);
		
			if (!Objects.equals(floatingRateIndex, _that.getFloatingRateIndex())) return false;
			if (!Objects.equals(inflationRateIndex, _that.getInflationRateIndex())) return false;
			if (!Objects.equals(indexTenor, _that.getIndexTenor())) return false;
			if (!Objects.equals(indexReferenceInformation, _that.getIndexReferenceInformation())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = 0;
			_result = 31 * _result + (floatingRateIndex != null ? floatingRateIndex.getClass().getName().hashCode() : 0);
			_result = 31 * _result + (inflationRateIndex != null ? inflationRateIndex.getClass().getName().hashCode() : 0);
			_result = 31 * _result + (indexTenor != null ? indexTenor.hashCode() : 0);
			_result = 31 * _result + (indexReferenceInformation != null ? indexReferenceInformation.hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "FloatingRateOption {" +
				"floatingRateIndex=" + this.floatingRateIndex + ", " +
				"inflationRateIndex=" + this.inflationRateIndex + ", " +
				"indexTenor=" + this.indexTenor + ", " +
				"indexReferenceInformation=" + this.indexReferenceInformation +
			'}';
		}
	}

	/*********************** Builder Implementation of FloatingRateOption  ***********************/
	class FloatingRateOptionBuilderImpl implements FloatingRateOption.FloatingRateOptionBuilder {
	
		protected FieldWithMetaFloatingRateIndexEnum.FieldWithMetaFloatingRateIndexEnumBuilder floatingRateIndex;
		protected FieldWithMetaInflationRateIndexEnum.FieldWithMetaInflationRateIndexEnumBuilder inflationRateIndex;
		protected Period.PeriodBuilder indexTenor;
		protected IndexReferenceInformation.IndexReferenceInformationBuilder indexReferenceInformation;
	
		public FloatingRateOptionBuilderImpl() {
		}
	
		@Override
		@RosettaAttribute("floatingRateIndex")
		public FieldWithMetaFloatingRateIndexEnum.FieldWithMetaFloatingRateIndexEnumBuilder getFloatingRateIndex() {
			return floatingRateIndex;
		}
		
		@Override
		public FieldWithMetaFloatingRateIndexEnum.FieldWithMetaFloatingRateIndexEnumBuilder getOrCreateFloatingRateIndex() {
			FieldWithMetaFloatingRateIndexEnum.FieldWithMetaFloatingRateIndexEnumBuilder result;
			if (floatingRateIndex!=null) {
				result = floatingRateIndex;
			}
			else {
				result = floatingRateIndex = FieldWithMetaFloatingRateIndexEnum.builder();
			}
			
			return result;
		}
		@Override
		@RosettaAttribute("inflationRateIndex")
		public FieldWithMetaInflationRateIndexEnum.FieldWithMetaInflationRateIndexEnumBuilder getInflationRateIndex() {
			return inflationRateIndex;
		}
		
		@Override
		public FieldWithMetaInflationRateIndexEnum.FieldWithMetaInflationRateIndexEnumBuilder getOrCreateInflationRateIndex() {
			FieldWithMetaInflationRateIndexEnum.FieldWithMetaInflationRateIndexEnumBuilder result;
			if (inflationRateIndex!=null) {
				result = inflationRateIndex;
			}
			else {
				result = inflationRateIndex = FieldWithMetaInflationRateIndexEnum.builder();
			}
			
			return result;
		}
		@Override
		@RosettaAttribute("indexTenor")
		public Period.PeriodBuilder getIndexTenor() {
			return indexTenor;
		}
		
		@Override
		public Period.PeriodBuilder getOrCreateIndexTenor() {
			Period.PeriodBuilder result;
			if (indexTenor!=null) {
				result = indexTenor;
			}
			else {
				result = indexTenor = Period.builder();
			}
			
			return result;
		}
		@Override
		@RosettaAttribute("indexReferenceInformation")
		public IndexReferenceInformation.IndexReferenceInformationBuilder getIndexReferenceInformation() {
			return indexReferenceInformation;
		}
		
		@Override
		public IndexReferenceInformation.IndexReferenceInformationBuilder getOrCreateIndexReferenceInformation() {
			IndexReferenceInformation.IndexReferenceInformationBuilder result;
			if (indexReferenceInformation!=null) {
				result = indexReferenceInformation;
			}
			else {
				result = indexReferenceInformation = IndexReferenceInformation.builder();
			}
			
			return result;
		}
	
		@Override
		@RosettaAttribute("floatingRateIndex")
		public FloatingRateOption.FloatingRateOptionBuilder setFloatingRateIndex(FieldWithMetaFloatingRateIndexEnum floatingRateIndex) {
			this.floatingRateIndex = floatingRateIndex==null?null:floatingRateIndex.toBuilder();
			return this;
		}
		@Override
		public FloatingRateOption.FloatingRateOptionBuilder setFloatingRateIndexValue(FloatingRateIndexEnum floatingRateIndex) {
			this.getOrCreateFloatingRateIndex().setValue(floatingRateIndex);
			return this;
		}
		@Override
		@RosettaAttribute("inflationRateIndex")
		public FloatingRateOption.FloatingRateOptionBuilder setInflationRateIndex(FieldWithMetaInflationRateIndexEnum inflationRateIndex) {
			this.inflationRateIndex = inflationRateIndex==null?null:inflationRateIndex.toBuilder();
			return this;
		}
		@Override
		public FloatingRateOption.FloatingRateOptionBuilder setInflationRateIndexValue(InflationRateIndexEnum inflationRateIndex) {
			this.getOrCreateInflationRateIndex().setValue(inflationRateIndex);
			return this;
		}
		@Override
		@RosettaAttribute("indexTenor")
		public FloatingRateOption.FloatingRateOptionBuilder setIndexTenor(Period indexTenor) {
			this.indexTenor = indexTenor==null?null:indexTenor.toBuilder();
			return this;
		}
		@Override
		@RosettaAttribute("indexReferenceInformation")
		public FloatingRateOption.FloatingRateOptionBuilder setIndexReferenceInformation(IndexReferenceInformation indexReferenceInformation) {
			this.indexReferenceInformation = indexReferenceInformation==null?null:indexReferenceInformation.toBuilder();
			return this;
		}
		
		@Override
		public FloatingRateOption build() {
			return new FloatingRateOption.FloatingRateOptionImpl(this);
		}
		
		@Override
		public FloatingRateOption.FloatingRateOptionBuilder toBuilder() {
			return this;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public FloatingRateOption.FloatingRateOptionBuilder prune() {
			if (floatingRateIndex!=null && !floatingRateIndex.prune().hasData()) floatingRateIndex = null;
			if (inflationRateIndex!=null && !inflationRateIndex.prune().hasData()) inflationRateIndex = null;
			if (indexTenor!=null && !indexTenor.prune().hasData()) indexTenor = null;
			if (indexReferenceInformation!=null && !indexReferenceInformation.prune().hasData()) indexReferenceInformation = null;
			return this;
		}
		
		@Override
		public boolean hasData() {
			if (getFloatingRateIndex()!=null) return true;
			if (getInflationRateIndex()!=null) return true;
			if (getIndexTenor()!=null && getIndexTenor().hasData()) return true;
			if (getIndexReferenceInformation()!=null && getIndexReferenceInformation().hasData()) return true;
			return false;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public FloatingRateOption.FloatingRateOptionBuilder merge(RosettaModelObjectBuilder other, BuilderMerger merger) {
			FloatingRateOption.FloatingRateOptionBuilder o = (FloatingRateOption.FloatingRateOptionBuilder) other;
			
			merger.mergeRosetta(getFloatingRateIndex(), o.getFloatingRateIndex(), this::setFloatingRateIndex);
			merger.mergeRosetta(getInflationRateIndex(), o.getInflationRateIndex(), this::setInflationRateIndex);
			merger.mergeRosetta(getIndexTenor(), o.getIndexTenor(), this::setIndexTenor);
			merger.mergeRosetta(getIndexReferenceInformation(), o.getIndexReferenceInformation(), this::setIndexReferenceInformation);
			
			return this;
		}
	
		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			FloatingRateOption _that = getType().cast(o);
		
			if (!Objects.equals(floatingRateIndex, _that.getFloatingRateIndex())) return false;
			if (!Objects.equals(inflationRateIndex, _that.getInflationRateIndex())) return false;
			if (!Objects.equals(indexTenor, _that.getIndexTenor())) return false;
			if (!Objects.equals(indexReferenceInformation, _that.getIndexReferenceInformation())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = 0;
			_result = 31 * _result + (floatingRateIndex != null ? floatingRateIndex.getClass().getName().hashCode() : 0);
			_result = 31 * _result + (inflationRateIndex != null ? inflationRateIndex.getClass().getName().hashCode() : 0);
			_result = 31 * _result + (indexTenor != null ? indexTenor.hashCode() : 0);
			_result = 31 * _result + (indexReferenceInformation != null ? indexReferenceInformation.hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "FloatingRateOptionBuilder {" +
				"floatingRateIndex=" + this.floatingRateIndex + ", " +
				"inflationRateIndex=" + this.inflationRateIndex + ", " +
				"indexTenor=" + this.indexTenor + ", " +
				"indexReferenceInformation=" + this.indexReferenceInformation +
			'}';
		}
	}
}
