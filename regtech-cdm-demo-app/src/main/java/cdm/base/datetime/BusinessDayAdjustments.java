package cdm.base.datetime;

import cdm.base.datetime.BusinessCenters;
import cdm.base.datetime.BusinessDayAdjustments;
import cdm.base.datetime.BusinessDayAdjustments.BusinessDayAdjustmentsBuilder;
import cdm.base.datetime.BusinessDayAdjustments.BusinessDayAdjustmentsBuilderImpl;
import cdm.base.datetime.BusinessDayAdjustments.BusinessDayAdjustmentsImpl;
import cdm.base.datetime.BusinessDayConventionEnum;
import cdm.base.datetime.meta.BusinessDayAdjustmentsMeta;
import com.rosetta.model.lib.GlobalKey;
import com.rosetta.model.lib.GlobalKey.GlobalKeyBuilder;
import com.rosetta.model.lib.RosettaModelObject;
import com.rosetta.model.lib.RosettaModelObjectBuilder;
import com.rosetta.model.lib.annotations.RosettaAttribute;
import com.rosetta.model.lib.annotations.RosettaDataType;
import com.rosetta.model.lib.meta.RosettaMetaData;
import com.rosetta.model.lib.path.RosettaPath;
import com.rosetta.model.lib.process.BuilderMerger;
import com.rosetta.model.lib.process.BuilderProcessor;
import com.rosetta.model.lib.process.Processor;
import com.rosetta.model.metafields.MetaFields;
import java.util.Objects;

import static java.util.Optional.ofNullable;

/**
 * A class defining the business day convention and financial business centers used for adjusting any relevant date if it would otherwise fall on a day that is not a business day in the specified business center.
 * @version ${project.version}
 */
@RosettaDataType(value="BusinessDayAdjustments", builder=BusinessDayAdjustments.BusinessDayAdjustmentsBuilderImpl.class, version="${project.version}")
public interface BusinessDayAdjustments extends RosettaModelObject, GlobalKey {

	BusinessDayAdjustmentsMeta metaData = new BusinessDayAdjustmentsMeta();

	/*********************** Getter Methods  ***********************/
	/**
	 * The convention for adjusting a date if it would otherwise fall on a day that is not a business day.
	 */
	BusinessDayConventionEnum getBusinessDayConvention();
	/**
	 * The business center(s), specified either explicitly or by reference to those specified somewhere else in the instance document.
	 */
	BusinessCenters getBusinessCenters();
	/**
	 */
	MetaFields getMeta();

	/*********************** Build Methods  ***********************/
	BusinessDayAdjustments build();
	
	BusinessDayAdjustments.BusinessDayAdjustmentsBuilder toBuilder();
	
	static BusinessDayAdjustments.BusinessDayAdjustmentsBuilder builder() {
		return new BusinessDayAdjustments.BusinessDayAdjustmentsBuilderImpl();
	}

	/*********************** Utility Methods  ***********************/
	@Override
	default RosettaMetaData<? extends BusinessDayAdjustments> metaData() {
		return metaData;
	}
	
	@Override
	default Class<? extends BusinessDayAdjustments> getType() {
		return BusinessDayAdjustments.class;
	}
	
	
	@Override
	default void process(RosettaPath path, Processor processor) {
		processor.processBasic(path.newSubPath("businessDayConvention"), BusinessDayConventionEnum.class, getBusinessDayConvention(), this);
		processRosetta(path.newSubPath("businessCenters"), processor, BusinessCenters.class, getBusinessCenters());
		processRosetta(path.newSubPath("meta"), processor, MetaFields.class, getMeta());
	}
	

	/*********************** Builder Interface  ***********************/
	interface BusinessDayAdjustmentsBuilder extends BusinessDayAdjustments, RosettaModelObjectBuilder {
		BusinessCenters.BusinessCentersBuilder getOrCreateBusinessCenters();
		BusinessCenters.BusinessCentersBuilder getBusinessCenters();
		MetaFields.MetaFieldsBuilder getOrCreateMeta();
		MetaFields.MetaFieldsBuilder getMeta();
		BusinessDayAdjustments.BusinessDayAdjustmentsBuilder setBusinessDayConvention(BusinessDayConventionEnum businessDayConvention);
		BusinessDayAdjustments.BusinessDayAdjustmentsBuilder setBusinessCenters(BusinessCenters businessCenters);
		BusinessDayAdjustments.BusinessDayAdjustmentsBuilder setMeta(MetaFields meta);

		@Override
		default void process(RosettaPath path, BuilderProcessor processor) {
			processor.processBasic(path.newSubPath("businessDayConvention"), BusinessDayConventionEnum.class, getBusinessDayConvention(), this);
			processRosetta(path.newSubPath("businessCenters"), processor, BusinessCenters.BusinessCentersBuilder.class, getBusinessCenters());
			processRosetta(path.newSubPath("meta"), processor, MetaFields.MetaFieldsBuilder.class, getMeta());
		}
		

		BusinessDayAdjustments.BusinessDayAdjustmentsBuilder prune();
	}

	/*********************** Immutable Implementation of BusinessDayAdjustments  ***********************/
	class BusinessDayAdjustmentsImpl implements BusinessDayAdjustments {
		private final BusinessDayConventionEnum businessDayConvention;
		private final BusinessCenters businessCenters;
		private final MetaFields meta;
		
		protected BusinessDayAdjustmentsImpl(BusinessDayAdjustments.BusinessDayAdjustmentsBuilder builder) {
			this.businessDayConvention = builder.getBusinessDayConvention();
			this.businessCenters = ofNullable(builder.getBusinessCenters()).map(f->f.build()).orElse(null);
			this.meta = ofNullable(builder.getMeta()).map(f->f.build()).orElse(null);
		}
		
		@Override
		@RosettaAttribute("businessDayConvention")
		public BusinessDayConventionEnum getBusinessDayConvention() {
			return businessDayConvention;
		}
		
		@Override
		@RosettaAttribute("businessCenters")
		public BusinessCenters getBusinessCenters() {
			return businessCenters;
		}
		
		@Override
		@RosettaAttribute("meta")
		public MetaFields getMeta() {
			return meta;
		}
		
		@Override
		public BusinessDayAdjustments build() {
			return this;
		}
		
		@Override
		public BusinessDayAdjustments.BusinessDayAdjustmentsBuilder toBuilder() {
			BusinessDayAdjustments.BusinessDayAdjustmentsBuilder builder = builder();
			setBuilderFields(builder);
			return builder;
		}
		
		protected void setBuilderFields(BusinessDayAdjustments.BusinessDayAdjustmentsBuilder builder) {
			ofNullable(getBusinessDayConvention()).ifPresent(builder::setBusinessDayConvention);
			ofNullable(getBusinessCenters()).ifPresent(builder::setBusinessCenters);
			ofNullable(getMeta()).ifPresent(builder::setMeta);
		}

		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			BusinessDayAdjustments _that = getType().cast(o);
		
			if (!Objects.equals(businessDayConvention, _that.getBusinessDayConvention())) return false;
			if (!Objects.equals(businessCenters, _that.getBusinessCenters())) return false;
			if (!Objects.equals(meta, _that.getMeta())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = 0;
			_result = 31 * _result + (businessDayConvention != null ? businessDayConvention.getClass().getName().hashCode() : 0);
			_result = 31 * _result + (businessCenters != null ? businessCenters.hashCode() : 0);
			_result = 31 * _result + (meta != null ? meta.hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "BusinessDayAdjustments {" +
				"businessDayConvention=" + this.businessDayConvention + ", " +
				"businessCenters=" + this.businessCenters + ", " +
				"meta=" + this.meta +
			'}';
		}
	}

	/*********************** Builder Implementation of BusinessDayAdjustments  ***********************/
	class BusinessDayAdjustmentsBuilderImpl implements BusinessDayAdjustments.BusinessDayAdjustmentsBuilder, GlobalKeyBuilder {
	
		protected BusinessDayConventionEnum businessDayConvention;
		protected BusinessCenters.BusinessCentersBuilder businessCenters;
		protected MetaFields.MetaFieldsBuilder meta;
	
		public BusinessDayAdjustmentsBuilderImpl() {
		}
	
		@Override
		@RosettaAttribute("businessDayConvention")
		public BusinessDayConventionEnum getBusinessDayConvention() {
			return businessDayConvention;
		}
		
		@Override
		@RosettaAttribute("businessCenters")
		public BusinessCenters.BusinessCentersBuilder getBusinessCenters() {
			return businessCenters;
		}
		
		@Override
		public BusinessCenters.BusinessCentersBuilder getOrCreateBusinessCenters() {
			BusinessCenters.BusinessCentersBuilder result;
			if (businessCenters!=null) {
				result = businessCenters;
			}
			else {
				result = businessCenters = BusinessCenters.builder();
			}
			
			return result;
		}
		@Override
		@RosettaAttribute("meta")
		public MetaFields.MetaFieldsBuilder getMeta() {
			return meta;
		}
		
		@Override
		public MetaFields.MetaFieldsBuilder getOrCreateMeta() {
			MetaFields.MetaFieldsBuilder result;
			if (meta!=null) {
				result = meta;
			}
			else {
				result = meta = MetaFields.builder();
			}
			
			return result;
		}
	
		@Override
		@RosettaAttribute("businessDayConvention")
		public BusinessDayAdjustments.BusinessDayAdjustmentsBuilder setBusinessDayConvention(BusinessDayConventionEnum businessDayConvention) {
			this.businessDayConvention = businessDayConvention==null?null:businessDayConvention;
			return this;
		}
		@Override
		@RosettaAttribute("businessCenters")
		public BusinessDayAdjustments.BusinessDayAdjustmentsBuilder setBusinessCenters(BusinessCenters businessCenters) {
			this.businessCenters = businessCenters==null?null:businessCenters.toBuilder();
			return this;
		}
		@Override
		@RosettaAttribute("meta")
		public BusinessDayAdjustments.BusinessDayAdjustmentsBuilder setMeta(MetaFields meta) {
			this.meta = meta==null?null:meta.toBuilder();
			return this;
		}
		
		@Override
		public BusinessDayAdjustments build() {
			return new BusinessDayAdjustments.BusinessDayAdjustmentsImpl(this);
		}
		
		@Override
		public BusinessDayAdjustments.BusinessDayAdjustmentsBuilder toBuilder() {
			return this;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public BusinessDayAdjustments.BusinessDayAdjustmentsBuilder prune() {
			if (businessCenters!=null && !businessCenters.prune().hasData()) businessCenters = null;
			if (meta!=null && !meta.prune().hasData()) meta = null;
			return this;
		}
		
		@Override
		public boolean hasData() {
			if (getBusinessDayConvention()!=null) return true;
			if (getBusinessCenters()!=null && getBusinessCenters().hasData()) return true;
			return false;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public BusinessDayAdjustments.BusinessDayAdjustmentsBuilder merge(RosettaModelObjectBuilder other, BuilderMerger merger) {
			BusinessDayAdjustments.BusinessDayAdjustmentsBuilder o = (BusinessDayAdjustments.BusinessDayAdjustmentsBuilder) other;
			
			merger.mergeRosetta(getBusinessCenters(), o.getBusinessCenters(), this::setBusinessCenters);
			merger.mergeRosetta(getMeta(), o.getMeta(), this::setMeta);
			
			merger.mergeBasic(getBusinessDayConvention(), o.getBusinessDayConvention(), this::setBusinessDayConvention);
			return this;
		}
	
		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			BusinessDayAdjustments _that = getType().cast(o);
		
			if (!Objects.equals(businessDayConvention, _that.getBusinessDayConvention())) return false;
			if (!Objects.equals(businessCenters, _that.getBusinessCenters())) return false;
			if (!Objects.equals(meta, _that.getMeta())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = 0;
			_result = 31 * _result + (businessDayConvention != null ? businessDayConvention.getClass().getName().hashCode() : 0);
			_result = 31 * _result + (businessCenters != null ? businessCenters.hashCode() : 0);
			_result = 31 * _result + (meta != null ? meta.hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "BusinessDayAdjustmentsBuilder {" +
				"businessDayConvention=" + this.businessDayConvention + ", " +
				"businessCenters=" + this.businessCenters + ", " +
				"meta=" + this.meta +
			'}';
		}
	}
}
