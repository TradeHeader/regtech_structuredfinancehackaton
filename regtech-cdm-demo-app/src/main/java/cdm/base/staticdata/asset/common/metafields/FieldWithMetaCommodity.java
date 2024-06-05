package cdm.base.staticdata.asset.common.metafields;

import cdm.base.staticdata.asset.common.Commodity;
import com.rosetta.model.lib.GlobalKey;
import com.rosetta.model.lib.GlobalKey.GlobalKeyBuilder;
import com.rosetta.model.lib.RosettaModelObject;
import com.rosetta.model.lib.RosettaModelObjectBuilder;
import com.rosetta.model.lib.annotations.RosettaAttribute;
import com.rosetta.model.lib.annotations.RosettaDataType;
import com.rosetta.model.lib.meta.BasicRosettaMetaData;
import com.rosetta.model.lib.meta.FieldWithMeta;
import com.rosetta.model.lib.meta.FieldWithMeta.FieldWithMetaBuilder;
import com.rosetta.model.lib.meta.RosettaMetaData;
import com.rosetta.model.lib.path.RosettaPath;
import com.rosetta.model.lib.process.BuilderMerger;
import com.rosetta.model.lib.process.BuilderProcessor;
import com.rosetta.model.lib.process.Processor;
import com.rosetta.model.metafields.MetaFields;
import java.util.Objects;

import static java.util.Optional.ofNullable;

/**
 * @version 1
 */
@RosettaDataType(value="FieldWithMetaCommodity", builder=FieldWithMetaCommodity.FieldWithMetaCommodityBuilderImpl.class, version="0.0.0")
public interface FieldWithMetaCommodity extends RosettaModelObject, FieldWithMeta<Commodity>, GlobalKey {

	FieldWithMetaCommodityMeta metaData = new FieldWithMetaCommodityMeta();

	/*********************** Getter Methods  ***********************/
	Commodity getValue();
	MetaFields getMeta();

	/*********************** Build Methods  ***********************/
	FieldWithMetaCommodity build();
	
	FieldWithMetaCommodity.FieldWithMetaCommodityBuilder toBuilder();
	
	static FieldWithMetaCommodity.FieldWithMetaCommodityBuilder builder() {
		return new FieldWithMetaCommodity.FieldWithMetaCommodityBuilderImpl();
	}

	/*********************** Utility Methods  ***********************/
	@Override
	default RosettaMetaData<? extends FieldWithMetaCommodity> metaData() {
		return metaData;
	}
	
	@Override
	default Class<? extends FieldWithMetaCommodity> getType() {
		return FieldWithMetaCommodity.class;
	}
	
	@Override
	default Class<Commodity> getValueType() {
		return Commodity.class;
	}
	
	@Override
	default void process(RosettaPath path, Processor processor) {
		processRosetta(path.newSubPath("value"), processor, Commodity.class, getValue());
		processRosetta(path.newSubPath("meta"), processor, MetaFields.class, getMeta());
	}
	

	/*********************** Builder Interface  ***********************/
	interface FieldWithMetaCommodityBuilder extends FieldWithMetaCommodity, RosettaModelObjectBuilder, GlobalKey.GlobalKeyBuilder, FieldWithMeta.FieldWithMetaBuilder<Commodity> {
		Commodity.CommodityBuilder getOrCreateValue();
		Commodity.CommodityBuilder getValue();
		MetaFields.MetaFieldsBuilder getOrCreateMeta();
		MetaFields.MetaFieldsBuilder getMeta();
		FieldWithMetaCommodity.FieldWithMetaCommodityBuilder setValue(Commodity value);
		FieldWithMetaCommodity.FieldWithMetaCommodityBuilder setMeta(MetaFields meta);

		@Override
		default void process(RosettaPath path, BuilderProcessor processor) {
			processRosetta(path.newSubPath("value"), processor, Commodity.CommodityBuilder.class, getValue());
			processRosetta(path.newSubPath("meta"), processor, MetaFields.MetaFieldsBuilder.class, getMeta());
		}
		

		FieldWithMetaCommodity.FieldWithMetaCommodityBuilder prune();
	}

	/*********************** Immutable Implementation of FieldWithMetaCommodity  ***********************/
	class FieldWithMetaCommodityImpl implements FieldWithMetaCommodity {
		private final Commodity value;
		private final MetaFields meta;
		
		protected FieldWithMetaCommodityImpl(FieldWithMetaCommodity.FieldWithMetaCommodityBuilder builder) {
			this.value = ofNullable(builder.getValue()).map(f->f.build()).orElse(null);
			this.meta = ofNullable(builder.getMeta()).map(f->f.build()).orElse(null);
		}
		
		@Override
		@RosettaAttribute("value")
		public Commodity getValue() {
			return value;
		}
		
		@Override
		@RosettaAttribute("meta")
		public MetaFields getMeta() {
			return meta;
		}
		
		@Override
		public FieldWithMetaCommodity build() {
			return this;
		}
		
		@Override
		public FieldWithMetaCommodity.FieldWithMetaCommodityBuilder toBuilder() {
			FieldWithMetaCommodity.FieldWithMetaCommodityBuilder builder = builder();
			setBuilderFields(builder);
			return builder;
		}
		
		protected void setBuilderFields(FieldWithMetaCommodity.FieldWithMetaCommodityBuilder builder) {
			ofNullable(getValue()).ifPresent(builder::setValue);
			ofNullable(getMeta()).ifPresent(builder::setMeta);
		}

		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			FieldWithMetaCommodity _that = getType().cast(o);
		
			if (!Objects.equals(value, _that.getValue())) return false;
			if (!Objects.equals(meta, _that.getMeta())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = 0;
			_result = 31 * _result + (value != null ? value.hashCode() : 0);
			_result = 31 * _result + (meta != null ? meta.hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "FieldWithMetaCommodity {" +
				"value=" + this.value + ", " +
				"meta=" + this.meta +
			'}';
		}
	}

	/*********************** Builder Implementation of FieldWithMetaCommodity  ***********************/
	class FieldWithMetaCommodityBuilderImpl implements FieldWithMetaCommodity.FieldWithMetaCommodityBuilder {
	
		protected Commodity.CommodityBuilder value;
		protected MetaFields.MetaFieldsBuilder meta;
	
		public FieldWithMetaCommodityBuilderImpl() {
		}
	
		@Override
		@RosettaAttribute("value")
		public Commodity.CommodityBuilder getValue() {
			return value;
		}
		
		@Override
		public Commodity.CommodityBuilder getOrCreateValue() {
			Commodity.CommodityBuilder result;
			if (value!=null) {
				result = value;
			}
			else {
				result = value = Commodity.builder();
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
		@RosettaAttribute("value")
		public FieldWithMetaCommodity.FieldWithMetaCommodityBuilder setValue(Commodity value) {
			this.value = value==null?null:value.toBuilder();
			return this;
		}
		@Override
		@RosettaAttribute("meta")
		public FieldWithMetaCommodity.FieldWithMetaCommodityBuilder setMeta(MetaFields meta) {
			this.meta = meta==null?null:meta.toBuilder();
			return this;
		}
		
		@Override
		public FieldWithMetaCommodity build() {
			return new FieldWithMetaCommodity.FieldWithMetaCommodityImpl(this);
		}
		
		@Override
		public FieldWithMetaCommodity.FieldWithMetaCommodityBuilder toBuilder() {
			return this;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public FieldWithMetaCommodity.FieldWithMetaCommodityBuilder prune() {
			if (value!=null && !value.prune().hasData()) value = null;
			if (meta!=null && !meta.prune().hasData()) meta = null;
			return this;
		}
		
		@Override
		public boolean hasData() {
			if (getValue()!=null && getValue().hasData()) return true;
			return false;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public FieldWithMetaCommodity.FieldWithMetaCommodityBuilder merge(RosettaModelObjectBuilder other, BuilderMerger merger) {
			FieldWithMetaCommodity.FieldWithMetaCommodityBuilder o = (FieldWithMetaCommodity.FieldWithMetaCommodityBuilder) other;
			
			merger.mergeRosetta(getValue(), o.getValue(), this::setValue);
			merger.mergeRosetta(getMeta(), o.getMeta(), this::setMeta);
			
			return this;
		}
	
		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			FieldWithMetaCommodity _that = getType().cast(o);
		
			if (!Objects.equals(value, _that.getValue())) return false;
			if (!Objects.equals(meta, _that.getMeta())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = 0;
			_result = 31 * _result + (value != null ? value.hashCode() : 0);
			_result = 31 * _result + (meta != null ? meta.hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "FieldWithMetaCommodityBuilder {" +
				"value=" + this.value + ", " +
				"meta=" + this.meta +
			'}';
		}
	}
}

class FieldWithMetaCommodityMeta extends BasicRosettaMetaData<FieldWithMetaCommodity>{

}
